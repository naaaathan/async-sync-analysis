package com.ufu.tcc.commonsdomain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "hotel_room")
public class HotelRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room roomId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotelId() {
        return hotelId;
    }

    public void setHotelId(Hotel hotel_id) {
        this.hotelId = hotel_id;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room room_id) {
        this.roomId = room_id;
    }
}
