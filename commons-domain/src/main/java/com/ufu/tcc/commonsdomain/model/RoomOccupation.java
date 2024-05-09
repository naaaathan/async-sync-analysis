package com.ufu.tcc.commonsdomain.model;

import com.ufu.tcc.commonsdomain.enums.Occupation;
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

    @Column(name = "room_occupation_date")
    private Date roomOccupationDate;

    @Column(name = "occupation")
    private Occupation occupation;

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

    public Date getRoomOccupationDate() {
        return roomOccupationDate;
    }

    public void setRoomOccupationDate(Date room_occupation_begin) {
        this.roomOccupationDate = room_occupation_begin;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
}
