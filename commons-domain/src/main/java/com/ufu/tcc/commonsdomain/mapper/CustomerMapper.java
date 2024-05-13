package com.ufu.tcc.commonsdomain.mapper;

import com.ufu.tcc.commonsdomain.model.Customer;
import com.ufu.tcc.commonsdomain.records.CustomerRecord;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerRecord toRecord(Customer customer) {
        return new CustomerRecord(customer.getId(), customer.getCustomerName());
    }

    public Customer toModel(CustomerRecord customerRecord) {
        Customer customer = new Customer();
        customer.setCustomerName(customerRecord.customerName());
        return customer;
    }

}
