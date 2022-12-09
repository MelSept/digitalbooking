package com.digitalbooking.apilodgings.dto.Image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.Image} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CreateImageDTO implements Serializable {

    @NotNull(message = "The 'url' field cannot be null.")
    @Max(message = "The  'url' field length must be less than or equal to 260 characters", value = 260)
    private String url;
}
