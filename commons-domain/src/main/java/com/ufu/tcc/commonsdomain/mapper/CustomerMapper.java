package com.ufu.tcc.commonsdomain.mapper;

import com.ufu.tcc.commonsdomain.model.User;
import com.ufu.tcc.commonsdomain.records.CustomerRecord;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerRecord toRecord(User user) {
        return new CustomerRecord(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    public User toModel(CustomerRecord customerRecord) {
        User user = new User();
        user.setId(customerRecord.customerId());
        user.setName(customerRecord.customerName());
        user.setEmail(customerRecord.email());
        user.setRole(customerRecord.role());
        return user;
    }

}
