package com.ufu.tcc.commonsdomain.service;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.model.RoomOccupation;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;
import com.ufu.tcc.commonsdomain.records.ReserveDataRecord;

import java.util.Date;
import java.util.List;

public interface RoomOccupationService {
    List<RoomOccupation> findRoomOccupationByHotelRoomIdAndDates(Long hotelRoomId, Date reserveBegin, Date reserveEnd);

    void save(HotelRoomRecord hotelRoomRecord, ReserveDataRecord reserveDataRecord);

    void update(List<RoomOccupation> roomOccupations, Occupation occupation);
}
