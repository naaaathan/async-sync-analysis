package com.ufu.tcc.commonsdomain.records;


import com.ufu.tcc.commonsdomain.enums.ReserveStatus;

import java.util.Date;

public record ReserveRecord(CustomerRecord customerRecord, Date reserveBegin, Date reserveEnd, HotelRoomRecord hotelRoom, ReserveStatus reserveStatus){}