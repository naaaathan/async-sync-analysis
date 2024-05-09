package com.ufu.tcc.commonsdomain.service;

import com.ufu.tcc.commonsdomain.records.HotelRecord;

import java.util.List;

public interface HotelService {

    List<HotelRecord> findHotelByCity(String city);

    List<HotelRecord> findHotelByRoomType(String roomType);

    List<HotelRecord> findHotelByRating(String rating);

    List<HotelRecord> findHotelByPrice(String price);

}
