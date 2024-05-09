package com.ufu.tcc.commonsdomain.service;

import com.ufu.tcc.commonsdomain.records.CustomerRecord;

public interface CustomerService {
    CustomerRecord findCustomerById(Long customerId);
}
