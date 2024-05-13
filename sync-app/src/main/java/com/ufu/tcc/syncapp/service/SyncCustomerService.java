package com.ufu.tcc.syncapp.service;

import com.ufu.tcc.commonsdomain.mapper.CustomerMapper;
import com.ufu.tcc.commonsdomain.records.CustomerRecord;
import com.ufu.tcc.commonsdomain.repository.CustomerRepository;
import com.ufu.tcc.commonsdomain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyncCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public SyncCustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerRecord findCustomerById(Long customerId) {
        return customerMapper.toRecord(
                customerRepository.findById(customerId)
                        .orElseThrow(() -> new RuntimeException("Customer not found"))
        );
    }
}
