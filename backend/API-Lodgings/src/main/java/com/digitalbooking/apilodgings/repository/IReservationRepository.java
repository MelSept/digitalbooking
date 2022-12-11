package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("IReservationRepository")
public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("select r from Reservation r where r.product.id = ?1 and r.product.deleted = false")
    List<Reservation> findAllBy_ProductId(Integer product_id);

    @Query("""
            select r from Reservation r
            where r.product.id = ?1 and
            (r.checkIn >= ?2 and r.checkIn <= ?3
            or
            r.checkOut >= ?2 and r.checkOut <= ?3
            or
            r.checkIn <= ?2 and r.checkOut >= ?3)
            """)
    List<Reservation> findBy_ProductId_And_CheckIn_Or_CheckOut_IsBetween(Integer productId, Date checkIn, Date checkOut);

    @Query("select r from Reservation r where r.user.id = ?1")
    List<Reservation> findAllBy_UserId(Integer userId);
}