package com.digitalbooking.apilodgings.dto.product;

import com.digitalbooking.apilodgings.dto.Image.CreateImageDTO;
import com.digitalbooking.apilodgings.dto.feature.CreateFeatureDTO;
import com.digitalbooking.apilodgings.dto.policy.CreatePolicyDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CreateProductDTO implements Serializable {

    @NotNull(message = "The 'title' field cannot be null.")
    @NotBlank(message = "The 'title' field cannot be empty.")
    private String title;

    @NotNull(message = "The 'description' field cannot be null.")
    @NotBlank(message = "The 'description' field cannot be empty.")
    private String description;

    @NotNull(message = "The 'thumbnail' field cannot be null.")
    @NotBlank(message = "The 'thumbnail' field cannot be empty.")
    private String thumbnail;

    @NotNull(message = "The 'images' field cannot be null.")
    @Size(min = 5, max = 25, message = "There must be at least 5 elements in the array.")
    private List<CreateImageDTO> images;

    @NotNull(message = "The 'features' field cannot be null.")
    @Size(min = 5, max = 25, message = "There must be at least 5 elements in the array.")
    private Set<CreateFeatureDTO> features;

    @NotNull(message = "The 'address' field cannot be null.")
    @NotBlank(message = "The 'address' field cannot be empty.")
    private String address;

    @NotNull(message = "The 'category' field cannot be null.")
    @NotBlank(message = "The 'category' field cannot be empty.")
    private String category;

    @NotNull(message = "The 'city' field cannot be null.")
    @NotBlank(message = "The 'city' field cannot be empty.")
    private String city;

    @NotNull(message = "The 'policy' field cannot be null.")
    private CreatePolicyDTO policy;
}
