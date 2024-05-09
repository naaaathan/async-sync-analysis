package com.ufu.tcc.syncapp.service;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.mapper.RoomOccupationMapper;
import com.ufu.tcc.commonsdomain.model.RoomOccupation;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;
import com.ufu.tcc.commonsdomain.records.ReserveDataRecord;
import com.ufu.tcc.commonsdomain.records.RoomOccupationRecord;
import com.ufu.tcc.commonsdomain.repository.RoomOccupationRepository;
import com.ufu.tcc.commonsdomain.service.RoomOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SyncRoomOccupationService implements RoomOccupationService {

    RoomOccupationMapper roomOccupationMapper;
    RoomOccupationRepository roomOccupationRepository;

    @Autowired
    public SyncRoomOccupationService(RoomOccupationMapper roomOccupationMapper, RoomOccupationRepository roomOccupationRepository) {
        this.roomOccupationMapper = roomOccupationMapper;
        this.roomOccupationRepository = roomOccupationRepository;
    }

    @Override
    public List<RoomOccupation> findRoomOccupationByHotelRoomIdAndDates(Long hotelRoomId, Date reserveBegin, Date reserveEnd) {
        return null;
    }

    public void save(HotelRoomRecord hotelRoomRecord, ReserveDataRecord reserveDataRecord) {

        RoomOccupationRecord roomOccupationRecord = new RoomOccupationRecord(hotelRoomRecord, reserveDataRecord.reserveBegin(), Occupation.OCCUPIED);
        this.roomOccupationRepository.save(roomOccupationMapper.toModel(roomOccupationRecord));
    }
}
