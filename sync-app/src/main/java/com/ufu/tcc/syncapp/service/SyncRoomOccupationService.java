package com.ufu.tcc.syncapp.service;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.mapper.RoomOccupationMapper;
import com.ufu.tcc.commonsdomain.model.HotelRoom;
import com.ufu.tcc.commonsdomain.model.RoomOccupation;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;
import com.ufu.tcc.commonsdomain.records.RoomOccupationRecord;
import com.ufu.tcc.commonsdomain.records.request.RoomOccupationRequestRecord;
import com.ufu.tcc.commonsdomain.records.response.RoomOccupationResponseRecord;
import com.ufu.tcc.commonsdomain.repository.RoomOccupationRepository;
import com.ufu.tcc.commonsdomain.service.HotelRoomService;
import com.ufu.tcc.commonsdomain.service.RoomOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SyncRoomOccupationService implements RoomOccupationService {

    private final RoomOccupationMapper roomOccupationMapper;
    private final RoomOccupationRepository roomOccupationRepository;
    private final HotelRoomService hotelRoomService;

    @Autowired
    public SyncRoomOccupationService(RoomOccupationMapper roomOccupationMapper, RoomOccupationRepository roomOccupationRepository, HotelRoomService hotelRoomService) {
        this.roomOccupationMapper = roomOccupationMapper;
        this.roomOccupationRepository = roomOccupationRepository;
        this.hotelRoomService = hotelRoomService;
    }


    @Override
    public List<RoomOccupation> findRoomOccupationByHotelRoomIdAndDates(Long hotelRoomId, LocalDateTime reserveBegin, LocalDateTime reserveEnd) {

        HotelRoom hotelRoom = hotelRoomService.findHotelById(hotelRoomId);

        return this.roomOccupationRepository
                .findRoomOccupationByHotelRoomAndRoomOccupationDateBeginGreaterThanEqualAndRoomOccupationDateEndLessThanEqual(hotelRoom, reserveBegin, reserveEnd);
    }

    public RoomOccupation save(RoomOccupation roomOccupation) {
        return this.roomOccupationRepository.save(roomOccupation);
    }

    @Override
    public void update(List<RoomOccupation> roomOccupations, Occupation occupation) {
        roomOccupations.forEach(roomOccupation -> {
            roomOccupation.setOccupation(occupation);
            this.roomOccupationRepository.save(roomOccupation);
        });
    }

    @Override
    public RoomOccupationResponseRecord createRoomOccupation(RoomOccupationRequestRecord roomOccupationRecord) {

        HotelRoomRecord hotelRoomRecord = hotelRoomService.findHotelRecordRoomById(roomOccupationRecord.hotelRoom().hotelRoomId());

        return roomOccupationMapper.toResponseRecord(this.save(roomOccupationMapper.fromRequestToModel(roomOccupationRecord, hotelRoomRecord)));
    }

    @Override
    public List<RoomOccupationResponseRecord> createRoomOccupationBatch(List<RoomOccupationRequestRecord> roomOccupationRecordList) {

        List<RoomOccupationResponseRecord> occupationResponseRecords = new ArrayList<>();

        roomOccupationRecordList.forEach(roomOccupationRecord -> {

            HotelRoomRecord hotelRoomRecord = hotelRoomService.findHotelRecordRoomById(roomOccupationRecord.hotelRoom().hotelRoomId());

            LocalDateTime beginOccupationDate = roomOccupationRecord.roomOccupationBeginDate();


            while (beginOccupationDate.isBefore(roomOccupationRecord.roomOccupationEndDate())) {

                RoomOccupationRequestRecord singleOccupation = new RoomOccupationRequestRecord(
                        roomOccupationRecord.hotelRoom(),
                        beginOccupationDate,
                        beginOccupationDate.plus(Duration.ofMinutes(1439))
                );

                occupationResponseRecords.add(
                        roomOccupationMapper.toResponseRecord(this.save(roomOccupationMapper.fromRequestToModel(singleOccupation, hotelRoomRecord)))
                );

                beginOccupationDate = beginOccupationDate.plus(Duration.ofDays(1));
            }
        });
        return occupationResponseRecords;
    }
}
