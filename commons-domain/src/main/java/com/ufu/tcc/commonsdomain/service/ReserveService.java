package com.ufu.tcc.commonsdomain.service;

import com.ufu.tcc.commonsdomain.records.PaymentMethod;
import com.ufu.tcc.commonsdomain.records.ReserveDataRecord;
import com.ufu.tcc.commonsdomain.records.ReserveRecord;

import java.util.List;

public interface ReserveService {

    void reserveHotelRoom(Long customerId, ReserveDataRecord reserveData, PaymentMethod paymentMethod);

    List<ReserveRecord> findReserveByCustomer(String customer);

}
