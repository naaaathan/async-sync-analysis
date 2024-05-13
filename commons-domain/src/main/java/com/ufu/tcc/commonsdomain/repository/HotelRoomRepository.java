package com.ufu.tcc.commonsdomain.repository;

import com.ufu.tcc.commonsdomain.model.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom,Long> {


}
