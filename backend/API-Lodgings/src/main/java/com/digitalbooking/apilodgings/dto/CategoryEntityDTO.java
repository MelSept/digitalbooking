package com.digitalbooking.apilodgings.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryEntityDTO {
    public int id;
    public String title;
    public String description;
    public String imageUrl;
}
