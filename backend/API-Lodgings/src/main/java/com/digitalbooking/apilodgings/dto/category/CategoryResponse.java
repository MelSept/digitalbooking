package com.digitalbooking.apilodgings.dto.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.Category} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CategoryResponse implements Serializable {

    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @NotNull(message = "The 'title' field cannot be null.")
    private String title;

    @NotNull(message = "The 'description' field cannot be null.")
    private String description;

    @NotNull(message = "The 'imageUrl' field cannot be null.")
    private String imageUrl;

    private Integer productCount;
}
