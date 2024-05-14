package com.ufu.tcc.commonsdomain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "room", schema = "hotel_tcc")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_tcc.room_seq")
    private Long id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_description")
    private String roomDescription;

    @Column(name = "room_capacity")
    private int roomCapacity;

    @Column(name = "price_per_day")
    private int pricePerDay;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String room_name) {
        this.roomName = room_name;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String room_description) {
        this.roomDescription = room_description;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public void setRoomCapacity(int room_capacity) {
        this.roomCapacity = room_capacity;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int price_per_day) {
        this.pricePerDay = price_per_day;
    }
}
