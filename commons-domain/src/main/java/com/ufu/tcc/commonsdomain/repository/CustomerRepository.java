package com.ufu.tcc.commonsdomain.repository;

import com.ufu.tcc.commonsdomain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {
}
