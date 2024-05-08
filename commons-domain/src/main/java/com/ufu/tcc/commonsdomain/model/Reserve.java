package com.ufu.tcc.commonsdomain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity(name = "reserve")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "reserve_begin")
    private Date reserveBegin;

    @Column(name = "reserve_end")
    private Date reserveEnd;

    @ManyToOne
    @JoinColumn(name = "hotel_room_id")
    private HotelRoom hotelRoomId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReserveBegin() {
        return reserveBegin;
    }

    public void setReserveBegin(Date reserveBegin) {
        this.reserveBegin = reserveBegin;
    }

    public Date getReserveEnd() {
        return reserveEnd;
    }

    public void setReserveEnd(Date reserveEnd) {
        this.reserveEnd = reserveEnd;
    }

    public HotelRoom getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(HotelRoom hotelRoomId) {
        this.hotelRoomId = hotelRoomId;
    }
}
