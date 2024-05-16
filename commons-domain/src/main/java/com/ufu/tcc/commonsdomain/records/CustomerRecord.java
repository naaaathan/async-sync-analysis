package com.ufu.tcc.commonsdomain.records;

import com.ufu.tcc.commonsdomain.enums.Role;

public record CustomerRecord(Long customerId, String customerName, String email, Role role) { }

