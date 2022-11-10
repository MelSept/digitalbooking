package com.digitalbooking.apilodgings.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.City} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CityDTO implements Serializable {

    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @NotNull(message = "The 'title' field cannot be null.")
    @NotBlank(message = "The 'title' field cannot be empty.")
    private String title;
}