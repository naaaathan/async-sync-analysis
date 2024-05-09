package com.ufu.tcc.commonsdomain.service;

import com.ufu.tcc.commonsdomain.records.PaymentMethodRecord;
import com.ufu.tcc.commonsdomain.records.ReserveDataRecord;
import com.ufu.tcc.commonsdomain.records.ReserveRecord;

import java.util.Date;
import java.util.List;

public interface ReserveService {

    void reserveHotelRoom(Long customerId, ReserveDataRecord reserveData, PaymentMethodRecord paymentMethod);

    List<ReserveRecord> findReserveByCustomer(String customer);

}
