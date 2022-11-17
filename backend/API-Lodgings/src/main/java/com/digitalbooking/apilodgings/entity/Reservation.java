package com.digitalbooking.apilodgings.entity;

import com.digitalbooking.apilodgings.enums.EStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

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
    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @Column(name = "check_in", nullable = false)
    public Date checkIn;

    @Column(name = "check_out", nullable = false)
    public Date checkOut;

    @Column(name = "start_time", nullable = false)
    public Time startTime;

    @Column(name = "finish_time", nullable = false)
    public Time finishTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 100)
    public EStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Reservation(Integer id, Date checkIn, Date checkOut, Time startTime, Time finishTime, EStatus status) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.status = status;
    }

    public Reservation() {
    }
}
