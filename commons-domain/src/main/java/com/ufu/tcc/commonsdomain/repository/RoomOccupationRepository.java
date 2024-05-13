package com.ufu.tcc.commonsdomain.repository;

import com.ufu.tcc.commonsdomain.model.HotelRoom;
import com.ufu.tcc.commonsdomain.model.RoomOccupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomOccupationRepository extends JpaRepository<RoomOccupation, Long> {

    List<RoomOccupation> findRoomOccupationByHotelRoomAndRoomOccupationDateBeginGreaterThanEqualAndRoomOccupationDateEndLessThanEqual(HotelRoom hotelRoom, Date begin, Date end);

}
