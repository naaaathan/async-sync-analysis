package com.ufu.tcc.commonsdomain.mapper;

import com.ufu.tcc.commonsdomain.model.HotelRoom;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;
import org.springframework.stereotype.Component;

@Component
public class HotelRoomMapper {

    public HotelRoomRecord toRecord(HotelRoom hotelRoom) {
        return new HotelRoomRecord(hotelRoom.getHotelId(), hotelRoom.getRoomId());
    }

    public HotelRoom toModel(HotelRoomRecord hotelRoomRecord) {
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setHotelId(hotelRoomRecord.hotelId());
        hotelRoom.setRoomId(hotelRoomRecord.roomId());
        return hotelRoom;
    }
}
