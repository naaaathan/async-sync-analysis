package com.ufu.tcc.syncapp.service;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.enums.ReserveStatus;
import com.ufu.tcc.commonsdomain.mapper.ReserveMapper;
import com.ufu.tcc.commonsdomain.model.RoomOccupation;
import com.ufu.tcc.commonsdomain.records.CustomerRecord;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;
import com.ufu.tcc.commonsdomain.records.PaymentMethod;
import com.ufu.tcc.commonsdomain.records.ReserveDataRecord;
import com.ufu.tcc.commonsdomain.records.ReserveRecord;
import com.ufu.tcc.commonsdomain.repository.ReserveRepository;
import com.ufu.tcc.commonsdomain.service.CustomerService;
import com.ufu.tcc.commonsdomain.service.HotelRoomService;
import com.ufu.tcc.commonsdomain.service.ReserveService;
import com.ufu.tcc.commonsdomain.service.RoomOccupationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SyncReserveService implements ReserveService {

    private final HotelRoomService hotelRoomService;
    private final ReserveRepository reserveRepository;
    private final ReserveMapper reserveMapper;
    private final CustomerService customerService;
    private final RoomOccupationService roomOccupationService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SyncReserveService(
            HotelRoomService hotelRoomService,
            ReserveRepository reserveRepository,
            ReserveMapper reserveMapper,
            CustomerService customerService,
            RoomOccupationService roomOccupationService) {
        this.hotelRoomService = hotelRoomService;
        this.reserveRepository = reserveRepository;
        this.reserveMapper = reserveMapper;
        this.customerService = customerService;
        this.roomOccupationService = roomOccupationService;
    }

    @Override
    @Transactional
    public void reserveHotelRoom(Long customerId, ReserveDataRecord reserveDataRecord, PaymentMethod paymentMethod) {

        List<RoomOccupation> roomOccupations = roomOccupationService.findRoomOccupationByHotelRoomIdAndDates(
                reserveDataRecord.hotelRoomId(),
                reserveDataRecord.reserveBegin(),
                reserveDataRecord.reserveEnd()
        );

        // Customers may see the same date available, but only one can reserve it then we use PESSIMISTIC_READ
        lockRoomsThatWillPossiblyBeOccupied(roomOccupations);

        HotelRoomRecord hotelRoomRecord = hotelRoomService.findHotelRecordRoomById(reserveDataRecord.hotelRoomId());
        CustomerRecord customerRecord = customerService.findCustomerById(customerId);

        roomOccupations.stream().filter(
                roomOccupation -> Occupation.OCCUPIED.equals(roomOccupation.getOccupation())
        ).findAny().ifPresent(
                roomOccupation -> {
                    throw new RuntimeException(String.format("Room is already occupied in the date %s", roomOccupation.getRoomOccupationDateBegin()));
                }
        );

        this.save(customerRecord, reserveDataRecord, hotelRoomRecord);

        if(roomOccupations.isEmpty()) {
            roomOccupationService.save(hotelRoomRecord, reserveDataRecord);
        }else {
            roomOccupationService.update(roomOccupations, Occupation.OCCUPIED);
        }
    }

    private void lockRoomsThatWillPossiblyBeOccupied(List<RoomOccupation> roomOccupations) {
        roomOccupations.forEach(
                roomOccupation -> entityManager.lock(roomOccupation, LockModeType.PESSIMISTIC_READ)
        );
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
