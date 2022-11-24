package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("IReservationRepository")
public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByProductId(Integer product_id);
    Reservation findByProduct_IdAndCheckInEqualsOrCheckOutEquals(Integer productId, Date checkIn, Date checkOut);
    Reservation findByProduct_IdAndCheckOutIsBetween(Integer productId, Date checkOut, Date checkOut2);
}