package com.ufu.tcc.commonsdomain.records;

import java.util.Date;

public record ReserveDataRecord(Date reserveBegin, Date reserveEnd, Long hotelRoomId, PaymentMethod paymentMethod){
}
