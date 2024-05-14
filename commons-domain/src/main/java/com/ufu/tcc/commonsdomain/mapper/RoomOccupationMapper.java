package com.ufu.tcc.commonsdomain.mapper;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.model.RoomOccupation;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;
import com.ufu.tcc.commonsdomain.records.RoomOccupationRecord;
import com.ufu.tcc.commonsdomain.records.request.RoomOccupationRequestRecord;
import com.ufu.tcc.commonsdomain.records.response.RoomOccupationResponseRecord;
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
        return new RoomOccupationRecord(
                hotelRoomMapper.toRecord(roomOccupation.getHotelRoom()),
                roomOccupation.getRoomOccupationDateBegin(),
                roomOccupation.getRoomOccupationDateEnd(),
                roomOccupation.getOccupation());
    }

    public RoomOccupation toModel(RoomOccupationRecord roomOccupationRecord) {
        RoomOccupation roomOccupation = new RoomOccupation();
        roomOccupation.setHotelRoom(hotelRoomMapper.toModel(roomOccupationRecord.hotelRoom()));
        roomOccupation.setRoomOccupationDateBegin(roomOccupationRecord.roomOccupationBeginDate());
        roomOccupation.setRoomOccupationDateEnd(roomOccupationRecord.roomOccupationEndDate());
        roomOccupation.setOccupation(roomOccupationRecord.occupation());
        return roomOccupation;
    }

    public RoomOccupation fromRequestToModel(RoomOccupationRequestRecord roomOccupationRequestRecord, HotelRoomRecord hotelRoomRecord) {
        RoomOccupation roomOccupation = new RoomOccupation();
        roomOccupation.setHotelRoom(hotelRoomMapper.toModel(hotelRoomRecord));
        roomOccupation.setRoomOccupationDateBegin(roomOccupationRequestRecord.roomOccupationBeginDate());
        roomOccupation.setRoomOccupationDateEnd(roomOccupationRequestRecord.roomOccupationEndDate());
        roomOccupation.setOccupation(Occupation.UNOCCUPIED);
        return roomOccupation;
    }

    public RoomOccupationResponseRecord toResponseRecord(RoomOccupation roomOccupation) {
        return new RoomOccupationResponseRecord(
                roomOccupation.getId(),
                hotelRoomMapper.toRecord(roomOccupation.getHotelRoom()),
                roomOccupation.getRoomOccupationDateBegin(),
                roomOccupation.getRoomOccupationDateEnd()
        );
    }
}
