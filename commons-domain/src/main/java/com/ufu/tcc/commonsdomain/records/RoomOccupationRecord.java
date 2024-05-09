package com.ufu.tcc.commonsdomain.records;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.model.HotelRoom;

import java.util.Date;

public record RoomOccupationRecord(HotelRoomRecord hotelRoom, Date roomOccupationDate, Occupation occupation) { }
