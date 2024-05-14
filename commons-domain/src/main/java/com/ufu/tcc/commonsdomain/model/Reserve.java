package com.ufu.tcc.commonsdomain.model;

import com.ufu.tcc.commonsdomain.enums.ReserveStatus;
import jakarta.persistence.Cache;
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

import java.util.Date;

@Entity
@Table(name = "reserve", schema = "hotel_tcc")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_tcc.reserve_seq")
    private Long id;

    @Column(name = "reserve_begin")
    private Date reserveBegin;

    @Column(name = "reserve_end")
    private Date reserveEnd;

    @ManyToOne
    @JoinColumn(name = "hotel_room_id")
    private HotelRoom hotelRoomId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ReserveStatus status;


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

    public ReserveStatus getStatus() {
        return status;
    }

    public void setStatus(ReserveStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
