package com.ufu.tcc.syncapp.service;

import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;
import com.ufu.tcc.commonsdomain.service.HotelRoomService;
import org.springframework.stereotype.Service;

@Service
public class SyncHotelRoomService implements HotelRoomService {
    @Override
    public HotelRoomRecord findHotelRoomById(Long hotelRoomId) {
        return null;
    }
}
