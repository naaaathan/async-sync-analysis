package com.ufu.tcc.commonsdomain.records;

import java.time.LocalDateTime;

public record ReserveDataRecord(LocalDateTime reserveBegin, LocalDateTime reserveEnd, Long hotelRoomId, PaymentMethod paymentMethod){
}
