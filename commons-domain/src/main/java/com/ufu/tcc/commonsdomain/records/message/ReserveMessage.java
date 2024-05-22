package com.ufu.tcc.commonsdomain.records.message;


import com.ufu.tcc.commonsdomain.records.PaymentMethod;
import com.ufu.tcc.commonsdomain.records.ReserveDataRecord;

public record ReserveMessage(Long customerId, ReserveDataRecord reserveDataRecord, PaymentMethod paymentMethod) {
}
