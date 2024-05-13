package com.ufu.tcc.commonsdomain.model;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "room_occupation", schema = "hotel_tcc")
public class RoomOccupation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hotel_tcc.room_occupation_seq")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_room_id")
    private HotelRoom hotelRoom;

    @Column(name = "room_occupation_begin")
    private Date roomOccupationDateBegin;

    @Column(name = "room_occupation_end")
    private Date roomOccupationDateEnd;

    @Column(name = "occupation")
    private Occupation occupation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public void setHotelRoom(HotelRoom hotel_room_id) {
        this.hotelRoom = hotel_room_id;
    }

    public Date getRoomOccupationDateBegin() {
        return roomOccupationDateBegin;
    }

    public void setRoomOccupationDateBegin(Date room_occupation_begin) {
        this.roomOccupationDateBegin = room_occupation_begin;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Date getRoomOccupationDateEnd() {
        return roomOccupationDateEnd;
    }

    public void setRoomOccupationDateEnd(Date roomOccupationDateEnd) {
        this.roomOccupationDateEnd = roomOccupationDateEnd;
    }
}
