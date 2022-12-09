package com.digitalbooking.apilodgings.dto.place;

import com.digitalbooking.apilodgings.dto.policy.CreatePolicyDTO;
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
public class CreatePlaceDTO implements Serializable {

    @NotNull(message = "The 'title' field cannot be null.")
    @NotBlank(message = "The 'title' field cannot be empty.")
    private String title;

    @NotNull(message = "The 'description' field cannot be null.")
    @NotBlank(message = "The 'description' field cannot be empty.")
    private String description;

    @NotNull(message = "The 'latitude' field cannot be null.")
    private float latitude = 0;

    @NotNull(message = "The 'longitude' field cannot be null.")
    private float longitude = 0;

    @NotNull(message = "The 'address' field cannot be null.")
    @NotBlank(message = "The 'address' field cannot be empty.")
    private String address;

    @NotNull(message = "The 'category' field cannot be null.")
    @NotBlank(message = "The 'city' field cannot be empty.")
    private String category;

    @NotNull(message = "The 'city' field cannot be null.")
    @NotBlank(message = "The 'city' field cannot be empty.")
    private String city;

    @NotNull(message = "The 'policy' field cannot be null.")
    private CreatePolicyDTO policy;
}
