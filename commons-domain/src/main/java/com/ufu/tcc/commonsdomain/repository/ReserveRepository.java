package com.ufu.tcc.commonsdomain.repository;

import com.ufu.tcc.commonsdomain.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
