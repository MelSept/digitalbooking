package com.digitalbooking.apilodgings.entity;

import com.digitalbooking.apilodgings.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "reservations")
public class Reservation {

    // Dev - Env
    /*
    @SequenceGenerator(name = "reservation_sequence", sequenceName = "reservation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "check_in", nullable = false)
    public Date checkIn;

    @Column(name = "check_out", nullable = false)
    public Date checkOut;

    @Column(name = "start_time", nullable = false)
    public LocalTime startTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 100)
    public EStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    public Reservation(Integer id, Date checkIn, Date checkOut, LocalTime startTime, EStatus status) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.startTime = startTime;
        this.status = status;
    }

    public Reservation() {
    }
}
