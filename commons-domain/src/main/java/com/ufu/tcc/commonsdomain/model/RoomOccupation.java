package com.ufu.tcc.commonsdomain.model;

import com.ufu.tcc.commonsdomain.enums.Occupation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_occupation", schema = "hotel_tcc")
public class RoomOccupation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_tcc.room_occupation_seq")
    @SequenceGenerator(name = "hotel_tcc.room_occupation_seq", sequenceName = "hotel_tcc.room_occupation_seq", allocationSize = 1, schema = "hotel_tcc")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_room_id")
    private HotelRoom hotelRoom;

    @Column(name = "room_occupation_begin")
    private LocalDateTime roomOccupationDateBegin;

    @Column(name = "room_occupation_end")
    private LocalDateTime roomOccupationDateEnd;

    @Column(name = "occupation")
    @Enumerated(EnumType.STRING)
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

    public LocalDateTime getRoomOccupationDateBegin() {
        return roomOccupationDateBegin;
    }

    public void setRoomOccupationDateBegin(LocalDateTime room_occupation_begin) {
        this.roomOccupationDateBegin = room_occupation_begin;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public LocalDateTime getRoomOccupationDateEnd() {
        return roomOccupationDateEnd;
    }

    public void setRoomOccupationDateEnd(LocalDateTime roomOccupationDateEnd) {
        this.roomOccupationDateEnd = roomOccupationDateEnd;
    }
}
