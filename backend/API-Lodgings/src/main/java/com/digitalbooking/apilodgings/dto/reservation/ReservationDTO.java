package com.digitalbooking.apilodgings.dto.reservation;

import com.digitalbooking.apilodgings.dto.product.ProductMiniDTO;
import com.digitalbooking.apilodgings.dto.user.UserDTO;
import com.digitalbooking.apilodgings.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.Reservation} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ReservationDTO implements Serializable {

    @NotNull(message = "The 'id' field cannot be null.")
    private final Integer id;

    private final Date checkIn;

    private final Date checkOut;

    private final Time startTime;

    private final Time finishTime;

    private final EStatus status;

    private final ProductMiniDTO product;

    private final UserDTO user;
}