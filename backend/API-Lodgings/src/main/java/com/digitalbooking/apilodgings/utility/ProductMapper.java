package com.digitalbooking.apilodgings.utility;

import com.digitalbooking.apilodgings.dto.product.CreateProductDTO;
import com.digitalbooking.apilodgings.entity.Product;

public class ProductMapper {


    public static Product fromCreateProductDTO(CreateProductDTO createProductDTO){
        Product product = new Product();
        product.setTitle(createProductDTO.getTitle());
        product.setDescription(createProductDTO.getDescription());
        product.setThumbnail(createProductDTO.getThumbnail());
        return product;
    }
}
