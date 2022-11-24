package com.digitalbooking.apilodgings.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.Reservation} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CreateReservationDTO {

    @FutureOrPresent(message = "The check-In date must be today or in the future.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;

    @FutureOrPresent(message = "The Check-Out date must be today or in the future.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @NotNull(message = "The field 'userId' must not be null")
    private Integer userId;

    @NotNull(message = "The field 'productId' must not be null")
    private Integer productId;
}
