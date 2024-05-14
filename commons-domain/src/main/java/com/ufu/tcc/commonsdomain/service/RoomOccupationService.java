package com.ufu.tcc.commonsdomain.service;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import com.ufu.tcc.commonsdomain.model.RoomOccupation;
import com.ufu.tcc.commonsdomain.records.request.RoomOccupationRequestRecord;
import com.ufu.tcc.commonsdomain.records.response.RoomOccupationResponseRecord;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomOccupationService {

    List<RoomOccupation> findRoomOccupationByHotelRoomIdAndDates(Long hotelRoomId, LocalDateTime reserveBegin, LocalDateTime reserveEnd);

    RoomOccupation save(RoomOccupation roomOccupation);

    void update(List<RoomOccupation> roomOccupations, Occupation occupation);

    RoomOccupationResponseRecord createRoomOccupation(RoomOccupationRequestRecord roomOccupationRecord);

    List<RoomOccupationResponseRecord> createRoomOccupationBatch(RoomOccupationRequestRecord roomOccupationRecord);
}
