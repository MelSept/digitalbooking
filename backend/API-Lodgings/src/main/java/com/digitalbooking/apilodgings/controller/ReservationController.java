package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.dto.reservation.CreateReservationDTO;
import com.digitalbooking.apilodgings.dto.reservation.ReservationDTO;
import com.digitalbooking.apilodgings.exception.BadRequestException;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.service.reservation.IReservationService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/reservation")
@Tag(name = "Reservation", description = "Endpoint to management reservations")
@SecurityScheme(
        name = "Bearer Authentication / Add to Request Header 'Key: Authorization - Value: Bearer <your token>'",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class ReservationController {

    private final IReservationService reservationService;

    @Autowired
    public ReservationController(@Qualifier("ReservationServiceImpl") IReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize(value = "hasRole('ADMIN') or hasRole('USER')")
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
