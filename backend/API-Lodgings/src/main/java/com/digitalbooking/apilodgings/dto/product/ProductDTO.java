package com.digitalbooking.apilodgings.dto.product;

import com.digitalbooking.apilodgings.dto.feature.FeatureDTO;
import com.digitalbooking.apilodgings.dto.Image.ImageDTO;
import com.digitalbooking.apilodgings.dto.place.PlaceDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.Product} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProductDTO implements Serializable {

    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

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
    private PlaceDTO place;

    @NotNull(message = "The 'images' field cannot be null.")
    @NotBlank(message = "The 'images' field cannot be empty.")
    private List<ImageDTO> images;

    @NotNull(message = "The 'features' field cannot be null.")
    @NotBlank(message = "The 'features' field cannot be empty.")
    private List<FeatureDTO> features;
}