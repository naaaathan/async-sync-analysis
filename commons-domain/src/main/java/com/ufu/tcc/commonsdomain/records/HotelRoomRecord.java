package com.ufu.tcc.commonsdomain.records;

import com.ufu.tcc.commonsdomain.model.Hotel;
import com.ufu.tcc.commonsdomain.model.Room;

public record HotelRoomRecord(Long hotelRoomId, Hotel hotelId, Room roomId){ }
