package com.ufu.tcc.syncapp.service;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.mapper.RoomOccupationMapper;
import com.ufu.tcc.commonsdomain.model.HotelRoom;
import com.ufu.tcc.commonsdomain.model.RoomOccupation;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;
import com.ufu.tcc.commonsdomain.records.ReserveDataRecord;
import com.ufu.tcc.commonsdomain.records.RoomOccupationRecord;
import com.ufu.tcc.commonsdomain.repository.RoomOccupationRepository;
import com.ufu.tcc.commonsdomain.service.HotelRoomService;
import com.ufu.tcc.commonsdomain.service.RoomOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<RoomOccupation> findRoomOccupationByHotelRoomIdAndDates(Long hotelRoomId, Date reserveBegin, Date reserveEnd) {

        HotelRoom hotelRoom = hotelRoomService.findHotelById(hotelRoomId);

        return this.roomOccupationRepository
                .findRoomOccupationByHotelRoomAndRoomOccupationDateBeginGreaterThanEqualAndRoomOccupationDateEndLessThanEqual(hotelRoom, reserveBegin, reserveEnd);
    }

    public void save(HotelRoomRecord hotelRoomRecord, ReserveDataRecord reserveDataRecord) {

        RoomOccupationRecord roomOccupationRecord = new RoomOccupationRecord(hotelRoomRecord, reserveDataRecord.reserveBegin(), reserveDataRecord.reserveEnd(), Occupation.OCCUPIED);
        this.roomOccupationRepository.save(roomOccupationMapper.toModel(roomOccupationRecord));
    }

    @Override
    public void update(List<RoomOccupation> roomOccupations, Occupation occupation) {
        roomOccupations.forEach(roomOccupation -> {
            roomOccupation.setOccupation(occupation);
            this.roomOccupationRepository.save(roomOccupation);
        });
    }
}
