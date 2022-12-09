package com.digitalbooking.apilodgings.dto.policy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.Policy} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PolicyDTO implements Serializable {

    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @NotNull(message = "The 'norm' field cannot be null.")
    @NotBlank(message = "The 'norm' field cannot be empty.")
    private String norm;

    @NotNull(message = "The 'security' field cannot be null.")
    @NotBlank(message = "The 'security' field cannot be empty.")
    private String security;

    @NotNull(message = "The 'cancellation' field cannot be null.")
    @NotBlank(message = "The 'cancellation' field cannot be empty.")
    private String cancellation;
}