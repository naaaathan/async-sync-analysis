package com.ufu.tcc.commonsdomain.records.request;

import java.time.LocalDateTime;

public record RoomOccupationRequestRecord(HotelRoomRequestRecord hotelRoom, LocalDateTime roomOccupationBeginDate, LocalDateTime roomOccupationEndDate) {
}
