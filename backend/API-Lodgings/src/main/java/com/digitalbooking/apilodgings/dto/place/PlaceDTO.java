package com.digitalbooking.apilodgings.dto.place;

import com.digitalbooking.apilodgings.dto.CityDTO;
import com.digitalbooking.apilodgings.dto.PolicyDTO;
import com.digitalbooking.apilodgings.dto.category.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.Place} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PlaceDTO implements Serializable {

    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @NotNull(message = "The 'title' field cannot be null.")
    @NotBlank(message = "The 'title' field cannot be empty.")
    private String title;

    @NotNull(message = "The 'description' field cannot be null.")
    @NotBlank(message = "The 'description' field cannot be empty.")
    private String description;

    @NotNull(message = "The 'latitude' field cannot be null.")
    @NotBlank(message = "The 'latitude' field cannot be empty.")
    private float latitude;

    @NotNull(message = "The 'longitude' field cannot be null.")
    @NotBlank(message = "The 'longitude' field cannot be empty.")
    private float longitude;

    @NotNull(message = "The 'address' field cannot be null.")
    @NotBlank(message = "The 'address' field cannot be empty.")
    private String address;

    private CategoryDTO category;

    private CityDTO city;

    private PolicyDTO policy;
}