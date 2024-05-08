package com.ufu.tcc.commonsdomain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity(name = "room_occupation")
public class RoomOccupation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_room_id")
    private HotelRoom hotelRoomId;

    @Column(name = "room_occupation_begin")
    private Date roomOccupationBegin;

    @Column(name = "room_occupation_end")
    private Date roomOccupationEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HotelRoom getHotelRoomId() {
        return hotelRoomId;
    }

    public void setHotelRoomId(HotelRoom hotel_room_id) {
        this.hotelRoomId = hotel_room_id;
    }

    public Date getRoomOccupationBegin() {
        return roomOccupationBegin;
    }

    public void setRoomOccupationBegin(Date room_occupation_begin) {
        this.roomOccupationBegin = room_occupation_begin;
    }

    public Date getRoomOccupationEnd() {
        return roomOccupationEnd;
    }

    public void setRoomOccupationEnd(Date room_occupation_end) {
        this.roomOccupationEnd = room_occupation_end;
    }
}
