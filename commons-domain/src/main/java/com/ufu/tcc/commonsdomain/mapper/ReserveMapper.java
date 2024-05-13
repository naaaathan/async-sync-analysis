package com.ufu.tcc.commonsdomain.mapper;

import com.ufu.tcc.commonsdomain.model.Reserve;
import com.ufu.tcc.commonsdomain.records.ReserveRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReserveMapper {

    private final HotelRoomMapper hotelRoomMapper;
    private final CustomerMapper customerMapper;

    @Autowired
    public ReserveMapper(HotelRoomMapper hotelRoomMapper, CustomerMapper customerMapper) {
        this.hotelRoomMapper = hotelRoomMapper;
        this.customerMapper = customerMapper;
    }

    public ReserveRecord toRecord(Reserve reserve) {
        return new ReserveRecord(
                customerMapper.toRecord(reserve.getCustomer()),
                reserve.getReserveBegin(),
                reserve.getReserveEnd(),
                hotelRoomMapper.toRecord(reserve.getHotelRoomId()),
                reserve.getStatus()
        );
    }

    public Reserve toModel(ReserveRecord reserveRecord) {
        Reserve reserve = new Reserve();
        reserve.setCustomer(customerMapper.toModel(reserveRecord.customerRecord()));
        reserve.setReserveBegin(reserveRecord.reserveBegin());
        reserve.setReserveEnd(reserveRecord.reserveEnd());
        reserve.setHotelRoomId(hotelRoomMapper.toModel(reserveRecord.hotelRoom()));
        reserve.setStatus(reserveRecord.reserveStatus());
        return reserve;
    }


}
