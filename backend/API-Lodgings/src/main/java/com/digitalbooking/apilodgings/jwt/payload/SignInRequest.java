package com.digitalbooking.apilodgings.jwt.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SignInRequest implements Serializable {

    @NotEmpty(message = "Field cannot be null or empty.")
    private String username;

    @NotEmpty(message = "Field cannot be null or empty.")
    private String password;
}
