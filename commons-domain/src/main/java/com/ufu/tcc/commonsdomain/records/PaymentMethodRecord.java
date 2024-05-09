package com.ufu.tcc.commonsdomain.records;

import com.ufu.tcc.commonsdomain.enums.PaymentMethodType;

public record PaymentMethodRecord(PaymentMethodType paymentMethodType, CardRecord cardRecord) {
}

