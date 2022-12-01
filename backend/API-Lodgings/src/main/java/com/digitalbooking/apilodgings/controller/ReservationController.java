package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.dto.reservation.CreateReservationDTO;
import com.digitalbooking.apilodgings.dto.reservation.ReservationDTO;
import com.digitalbooking.apilodgings.exception.BadRequestException;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.service.reservation.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

    private final IReservationService reservationService;

    @Autowired
    public ReservationController(@Qualifier("ReservationServiceImpl") IReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody CreateReservationDTO createReservationDTO) throws NotFoundException, BadRequestException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        ReservationDTO reservationCreated = reservationService.createReservation(createReservationDTO);

        return new ResponseEntity<>(reservationCreated, headers, HttpStatus.CREATED);
    }


    @GetMapping(path = {"/productId/{id}"})
    public ResponseEntity<List<ReservationDTO>> findAllReservationsByProductId(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ReservationDTO> categoriesFound = reservationService.findAllReservationsByProductId(id);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"/userId/{id}"})
    public ResponseEntity<List<ReservationDTO>> findAllReservationsByUserId(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ReservationDTO> categoriesFound = reservationService.findAllReservationsByUserId(id);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }
}
