package com.ufu.tcc.asyncapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufu.tcc.commonsdomain.clients.PaymentClient;
import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.enums.PaymentStatus;
import com.ufu.tcc.commonsdomain.enums.ReserveStatus;
import com.ufu.tcc.commonsdomain.exception.NoRoomOccupationFoundException;
import com.ufu.tcc.commonsdomain.mapper.ReserveMapper;
import com.ufu.tcc.commonsdomain.model.RoomOccupation;
import com.ufu.tcc.commonsdomain.records.CustomerRecord;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;
import com.ufu.tcc.commonsdomain.records.PaymentMethod;
import com.ufu.tcc.commonsdomain.records.PaymentResponse;
import com.ufu.tcc.commonsdomain.records.ReserveDataRecord;
import com.ufu.tcc.commonsdomain.records.ReserveRecord;
import com.ufu.tcc.commonsdomain.records.message.ReserveMessage;
import com.ufu.tcc.commonsdomain.repository.ReserveRepository;
import com.ufu.tcc.commonsdomain.service.CustomerService;
import com.ufu.tcc.commonsdomain.service.HotelRoomService;
import com.ufu.tcc.commonsdomain.service.ReserveService;
import com.ufu.tcc.commonsdomain.service.RoomOccupationService;
import com.ufu.tcc.commonsdomain.service.SESService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsyncReserveService implements ReserveService {

    private static final Logger LOGGER = LogManager.getLogger(AsyncReserveService.class);

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final RoomOccupationService roomOccupationService;
    private final PaymentClient paymentClient;
    private final ReserveRepository reserveRepository;
    private final ReserveMapper reserveMapper;
    private final CustomerService customerService;
    private final HotelRoomService hotelRoomService;
    private final SESService sesService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AsyncReserveService(
            QueueMessagingTemplate queueMessagingTemplate,
            RoomOccupationService roomOccupationService,
            PaymentClient paymentClient,
            ReserveRepository reserveRepository,
            ReserveMapper reserveMapper,
            CustomerService customerService,
            HotelRoomService hotelRoomService,
            SESService sesService,
            ObjectMapper objectMapper) {
        this.queueMessagingTemplate = queueMessagingTemplate;
        this.roomOccupationService = roomOccupationService;
        this.paymentClient = paymentClient;
        this.reserveRepository = reserveRepository;
        this.reserveMapper = reserveMapper;
        this.customerService = customerService;
        this.hotelRoomService = hotelRoomService;
        this.sesService = sesService;
        this.objectMapper = objectMapper;
    }


    @Override
    public void reserveHotelRoom(Long customerId, ReserveDataRecord reserveDataRecord, PaymentMethod paymentMethod) {

        List<RoomOccupation> roomOccupations = roomOccupationService.findRoomOccupationByHotelRoomIdAndDates(
                reserveDataRecord.hotelRoomId(),
                reserveDataRecord.reserveBegin(),
                reserveDataRecord.reserveEnd()
        );

        if (roomOccupations.isEmpty()) {
            throw new NoRoomOccupationFoundException();
        }

        roomOccupations.stream().filter(
                roomOccupation -> Occupation.OCCUPIED.equals(roomOccupation.getOccupation())
        ).findAny().ifPresent(
                roomOccupation -> {
                    throw new RuntimeException(String.format("Room is already occupied in the date %s", roomOccupation.getRoomOccupationDateBegin()));
                }
        );

        ReserveMessage reserveMessage = new ReserveMessage(customerId,reserveDataRecord, paymentMethod);

        String reserveMessageJson;
        try {
            reserveMessageJson = objectMapper.writeValueAsString(reserveMessage);
        } catch (Exception e) {
            LOGGER.error("Error serializing message: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        this.queueMessagingTemplate.send("hotel_reservation_queue", MessageBuilder.withPayload(reserveMessageJson).build());

    }

    @SqsListener("hotel_reservation_queue")
    public void processReservation(@Header("MessageId") String messageId, @Payload String message) {

        LOGGER.info("Processing message with id: {} and message : {}", messageId, message);

        ReserveMessage reserveMessage;

        try {
            reserveMessage = objectMapper.readValue(message, ReserveMessage.class);
        } catch (Exception e) {
            LOGGER.error("Error in deserialization of message: {}", message);
            throw new RuntimeException(e);
        }

        ReserveDataRecord reserveDataRecord = reserveMessage.reserveDataRecord();
        PaymentMethod paymentMethod = reserveMessage.paymentMethod();
        Long customerId = reserveMessage.customerId();

        HotelRoomRecord hotelRoomRecord = hotelRoomService.findHotelRecordRoomById(reserveDataRecord.hotelRoomId());
        CustomerRecord customerRecord = customerService.findCustomerById(customerId);

        PaymentResponse paymentResponse = paymentClient.processPayment(paymentMethod).getBody();

        if (paymentResponse != null && !PaymentStatus.SUCCESS.name().equals(paymentResponse.status())) {
            throw new RuntimeException("Payment failed");
        }


        this.save(customerRecord, reserveDataRecord, hotelRoomRecord);

        List<RoomOccupation> roomOccupations = roomOccupationService.findRoomOccupationByHotelRoomIdAndDates(
                reserveDataRecord.hotelRoomId(),
                reserveDataRecord.reserveBegin(),
                reserveDataRecord.reserveEnd()
        );

        roomOccupationService.update(roomOccupations, Occupation.OCCUPIED);

        sesService.sendEmail(
                "reserve@reserve.com",
                customerRecord.email(),
                "Reserve confirmed",
                "Your reserve has been confirmed"
        );

        LOGGER.info("Ending processing message");

    }

    @Override
    public List<ReserveRecord> findReserveByCustomer(String customer) {
        return null;
    }

    public void save(CustomerRecord customerRecord, ReserveDataRecord reserveDataRecord, HotelRoomRecord hotelRoomRecord) {
        ReserveRecord reserveRecord = new ReserveRecord(customerRecord, reserveDataRecord.reserveBegin(), reserveDataRecord.reserveEnd(), hotelRoomRecord, ReserveStatus.PENDING);
        this.reserveRepository.save(reserveMapper.toModel(reserveRecord));
    }
}
