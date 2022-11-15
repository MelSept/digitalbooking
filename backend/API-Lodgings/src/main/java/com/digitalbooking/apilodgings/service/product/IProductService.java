package com.digitalbooking.apilodgings.service.product;

import com.digitalbooking.apilodgings.dto.product.ProductDTO;
import com.digitalbooking.apilodgings.dto.product.ProductMiniDTO;
import com.digitalbooking.apilodgings.exception.NotFoundException;

import java.util.List;

public interface IProductService {

    ProductDTO createProduct(ProductDTO productRequestDTO);

    ProductDTO findProductById(Integer productId) throws NotFoundException;

    ProductDTO findProductByTitle(String productTitle) throws NotFoundException;

    List<ProductMiniDTO> findAllProducts();

    List<ProductMiniDTO> findAllProductsRandom(Integer quantity);

    List<ProductMiniDTO> findAllProductsByCategoryTitle(String categoryTitle);

    List<ProductMiniDTO> findAllProductsByCityTitle(String cityTitle);

}
