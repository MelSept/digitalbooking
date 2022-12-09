package com.digitalbooking.apilodgings.service.reservation;

import com.digitalbooking.apilodgings.dto.reservation.CreateReservationDTO;
import com.digitalbooking.apilodgings.dto.reservation.ReservationDTO;
import com.digitalbooking.apilodgings.exception.BadRequestException;
import com.digitalbooking.apilodgings.exception.NotFoundException;

import java.util.List;

public interface IReservationService {

    ReservationDTO createReservation(CreateReservationDTO reservationDTO) throws NotFoundException, BadRequestException;
    List<ReservationDTO> findAllReservationsByProductId(Integer productId);

    List<ReservationDTO> findAllReservationsByUserId(Integer userId);
}
