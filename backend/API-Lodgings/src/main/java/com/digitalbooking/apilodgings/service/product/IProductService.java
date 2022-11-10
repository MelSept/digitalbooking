package com.digitalbooking.apilodgings.service.product;

import com.digitalbooking.apilodgings.dto.ProductDTO;
import com.digitalbooking.apilodgings.exception.NotFoundException;

import java.util.List;

public interface IProductService {

    ProductDTO findProductById(Integer productId) throws NotFoundException;
    List<ProductDTO> findAllProducts();
    List<ProductDTO> findAllProductsByCategoryTitle(String categoryTitle);
    List<ProductDTO> findAllProductsByCityTitle(String cityTitle);

}
