package com.ufu.tcc.syncapp.service;

import com.ufu.tcc.commonsdomain.records.CustomerRecord;
import com.ufu.tcc.commonsdomain.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class SyncCustomerService implements CustomerService {
    @Override
    public CustomerRecord findCustomerById(Long customerId) {
        return null;
    }
}
