package com.ufu.tcc.commonsdomain.service;

import com.ufu.tcc.commonsdomain.model.HotelRoom;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;

public interface HotelRoomService {

    HotelRoomRecord findHotelRecordRoomById(Long hotelRoomId);

    HotelRoom findHotelById(Long hotelRoomId);

    void save(HotelRoomRecord hotelRoomRecord);
}
