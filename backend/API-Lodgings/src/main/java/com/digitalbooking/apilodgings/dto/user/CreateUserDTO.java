package com.digitalbooking.apilodgings.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CreateUserDTO implements Serializable {

    @NotEmpty(message = "Field cannot be null or empty.")
    @Size(message = "Length field must be between min: 2 and max: 80. in length", min = 2, max = 80)
    private String firstName;

    @NotEmpty(message = "Field cannot be null or empty.")
    @Size(message = "Length field must be between min: 2 and max: 80. in length", min = 2, max = 80)
    private String lastName;

    @NotEmpty(message = "Field cannot be null or empty.")
    @Size(message = "Length field must be between min: 2 and max: 20. in length", min = 6, max = 20)
    private String username;

    @NotEmpty(message = "Field cannot be null or empty.")
    @Email(message = "The field format must match: example@email.com", regexp = "^(.+)@(\\S+)$")
    private String email;

    @NotEmpty(message = "Field cannot be null or empty.")
    @Size(message = "Length field must be between min: 8 and max: 20. in length", min = 8, max = 20)
    private String password;

    @NotEmpty(message = "Field cannot be null or empty.")
    @Size(message = "Length field must be between min: 2 and max: 60. in length", min = 2, max = 80)
    private String city;
}

