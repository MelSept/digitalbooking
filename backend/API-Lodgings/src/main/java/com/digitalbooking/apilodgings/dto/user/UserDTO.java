package com.digitalbooking.apilodgings.dto.user;

import com.digitalbooking.apilodgings.dto.role.RoleDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.User} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDTO implements Serializable {

    @NotNull(message = "The 'id' field cannot be null.")
    private final Integer id;

    private final String firstName;

    private final String lastName;

    private final String username;

    private final String email;

    private final String city;

    private final RoleDTO role;
}