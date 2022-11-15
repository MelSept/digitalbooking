package com.digitalbooking.apilodgings.dto.product;

import com.digitalbooking.apilodgings.dto.FeatureDTO;
import com.digitalbooking.apilodgings.dto.ImageDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProductRequestDTO implements Serializable {

    @NotNull(message = "The 'title' field cannot be null.")
    @NotBlank(message = "The 'title' field cannot be empty.")
    private String title;

    @NotNull(message = "The 'description' field cannot be null.")
    @NotBlank(message = "The 'description' field cannot be empty.")
    private String description;

    @NotNull(message = "The 'available' field cannot be null.")
    @NotBlank(message = "The 'available' field cannot be empty.")
    private boolean available;

    @Column(name = "thumbnail")
    @NotNull(message = "The 'thumbnail' field cannot be null.")
    @NotBlank(message = "The 'thumbnail' field cannot be empty.")
    private String thumbnail;

    @NotNull(message = "The 'place' field cannot be null.")
    @NotBlank(message = "The 'place' field cannot be empty.")
    private Integer placeId;

    @NotNull(message = "The 'images' field cannot be null.")
    @NotBlank(message = "The 'images' field cannot be empty.")
    private Set<ImageDTO> images;

    @NotNull(message = "The 'features' field cannot be null.")
    @NotBlank(message = "The 'features' field cannot be empty.")
    private Set<FeatureDTO> features;
}
