package com.digitalbooking.apilodgings.dto.role;

import com.digitalbooking.apilodgings.enums.ERole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.Role} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RoleDTO implements Serializable {

    @NotNull(message = "The 'id' field cannot be null.")
    private final Integer id;

    private final ERole title;
}