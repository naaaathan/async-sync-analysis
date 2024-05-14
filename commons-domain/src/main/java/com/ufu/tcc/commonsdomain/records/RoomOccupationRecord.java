package com.ufu.tcc.commonsdomain.records;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.model.HotelRoom;

import java.time.LocalDateTime;

public record RoomOccupationRecord(HotelRoomRecord hotelRoom, LocalDateTime roomOccupationBeginDate, LocalDateTime roomOccupationEndDate, Occupation occupation) { }
