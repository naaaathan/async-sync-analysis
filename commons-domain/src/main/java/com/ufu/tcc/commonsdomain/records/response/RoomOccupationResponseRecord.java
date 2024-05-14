package com.ufu.tcc.commonsdomain.records.response;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;

import java.time.LocalDateTime;

public record RoomOccupationResponseRecord(Long roomOccupationId, HotelRoomRecord hotelRoom, LocalDateTime roomOccupationBeginDate, LocalDateTime roomOccupationEndDate) {
}
