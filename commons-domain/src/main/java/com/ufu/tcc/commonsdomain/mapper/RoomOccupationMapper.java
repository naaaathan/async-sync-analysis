package com.ufu.tcc.commonsdomain.mapper;

import com.ufu.tcc.commonsdomain.model.RoomOccupation;
import com.ufu.tcc.commonsdomain.records.RoomOccupationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomOccupationMapper {

    private final HotelRoomMapper hotelRoomMapper;

    @Autowired
    public RoomOccupationMapper(HotelRoomMapper hotelRoomMapper) {
        this.hotelRoomMapper = hotelRoomMapper;
    }

    public RoomOccupationRecord toRecord(RoomOccupation roomOccupation) {
        return new RoomOccupationRecord(hotelRoomMapper.toRecord(roomOccupation.getHotelRoomId()), roomOccupation.getRoomOccupationDate(), roomOccupation.getOccupation());
    }

    public RoomOccupation toModel(RoomOccupationRecord roomOccupationRecord) {
        RoomOccupation roomOccupation = new RoomOccupation();
        roomOccupation.setHotelRoomId(hotelRoomMapper.toModel(roomOccupationRecord.hotelRoom()));
        roomOccupation.setRoomOccupationDate(roomOccupationRecord.roomOccupationDate());
        return roomOccupation;
    }
}
