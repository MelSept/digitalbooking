package com.digitalbooking.apilodgings.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.Reservation} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ReservationDTO implements Serializable {

    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;


    @NotBlank(message = "The Check-In date must not be blank or null.")
    @NotEmpty(message = "The Check-In date must not be empty or contain whitespace.")
    @FutureOrPresent(message = "The check-In date must be today or in the future.")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$",
            message = "The Check-In date must have the pattern 'yyyy-mm-dd'")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date checkIn;


    @NotBlank(message = "The Check-Out date must not be blank or null.")
    @NotEmpty(message = "The Check-Out date must not be empty or contain whitespace.")
    @FutureOrPresent(message = "The Check-Out date must be today or in the future.")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$",
            message = "The Check-In date must have the pattern 'yyyy-mm-dd'")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date checkOut;

    @NotBlank(message = "The Start-Time must not be blank or null.")
    @NotEmpty(message = "The Start-Time must not be empty or contain whitespace.")
    @FutureOrPresent(message = "The Start-Time must be today or in the future.")
    @Pattern(regexp = "^(?:(?:([01]?\\d|2[0-3]):)?([0-5]?\\d):)?([0-5]?\\d)$",
            message = "The Start-Time must have the pattern 'yyyy-mm-dd'")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;
}