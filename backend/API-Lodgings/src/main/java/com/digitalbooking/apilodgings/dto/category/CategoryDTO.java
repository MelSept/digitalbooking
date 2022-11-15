package com.digitalbooking.apilodgings.dto.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CategoryDTO {
    @NotNull(message = "The 'id' field cannot be null.")
    public Integer id;
    @NotNull(message = "The 'title' field cannot be null.")
    public String title;
    @NotNull(message = "The 'description' field cannot be null.")
    public String description;
    @NotNull(message = "The 'imageUrl' field cannot be null.")
    public String imageUrl;
}
