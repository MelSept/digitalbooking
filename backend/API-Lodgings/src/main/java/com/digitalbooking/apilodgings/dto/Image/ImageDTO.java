package com.digitalbooking.apilodgings.dto.Image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.digitalbooking.apilodgings.entity.Image} entity
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ImageDTO implements Serializable, Comparable<ImageDTO> {

    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @NotNull(message = "The 'title' field cannot be null.")
    private String title;

    @NotNull(message = "The 'url' field cannot be null.")
    private String url;


    @Override
    public int compareTo(ImageDTO o) {
        int compareId = o.getId();

        //  For Ascending order
        return this.id - compareId;
    }
}