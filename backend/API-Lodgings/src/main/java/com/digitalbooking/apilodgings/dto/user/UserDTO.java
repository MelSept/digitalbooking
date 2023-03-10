package com.digitalbooking.apilodgings.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.User} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDTO implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String city;
}