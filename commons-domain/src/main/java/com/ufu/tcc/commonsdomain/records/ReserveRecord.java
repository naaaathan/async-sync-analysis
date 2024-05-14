package com.ufu.tcc.commonsdomain.records;


import com.ufu.tcc.commonsdomain.enums.ReserveStatus;

import java.time.LocalDateTime;

public record ReserveRecord(CustomerRecord customerRecord, LocalDateTime reserveBegin, LocalDateTime reserveEnd, HotelRoomRecord hotelRoom, ReserveStatus reserveStatus){}