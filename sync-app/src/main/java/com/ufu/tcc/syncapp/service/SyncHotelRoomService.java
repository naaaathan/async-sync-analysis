package com.ufu.tcc.syncapp.service;

import com.ufu.tcc.commonsdomain.mapper.HotelRoomMapper;
import com.ufu.tcc.commonsdomain.model.HotelRoom;
import com.ufu.tcc.commonsdomain.records.HotelRoomRecord;
import com.ufu.tcc.commonsdomain.repository.HotelRoomRepository;
import com.ufu.tcc.commonsdomain.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyncHotelRoomService implements HotelRoomService {

    private final HotelRoomRepository hotelRoomRepository;
    private final HotelRoomMapper hotelRoomMapper;

    @Autowired
    public SyncHotelRoomService(HotelRoomRepository hotelRoomRepository, HotelRoomMapper hotelRoomMapper) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.hotelRoomMapper = hotelRoomMapper;
    }

    @Override
    public HotelRoomRecord findHotelRecordRoomById(Long hotelRoomId) {
        return hotelRoomMapper.toRecord(findHotelById(hotelRoomId));
    }

    @Override
    public HotelRoom findHotelById(Long hotelRoomId) {
        return hotelRoomRepository.findById(hotelRoomId)
                .orElseThrow(() -> new RuntimeException("Hotel Room not found"));
    }

    @Override
    public void save(HotelRoomRecord hotelRoomRecord) {
        hotelRoomRepository.save(hotelRoomMapper.toModel(hotelRoomRecord));
    }


}
