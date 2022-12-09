package com.digitalbooking.apilodgings.service.product;

import com.digitalbooking.apilodgings.dto.product.CreateProductDTO;
import com.digitalbooking.apilodgings.dto.product.ProductDTO;
import com.digitalbooking.apilodgings.dto.product.ProductSmallDTO;
import com.digitalbooking.apilodgings.exception.NotFoundException;

import java.util.Date;
import java.util.List;

public interface IProductService {

    ProductDTO createProduct(CreateProductDTO productRequestDTO) throws NotFoundException;

    ProductDTO findProductById(Integer productId) throws NotFoundException;

    ProductDTO findProductByTitle(String productTitle) throws NotFoundException;

    List<ProductSmallDTO> findAllProducts();

    boolean deleteById(Integer id) throws NotFoundException;

    List<ProductSmallDTO> findAllProductsRandom(Integer quantity);

    List<ProductSmallDTO> findAllProductsByCategoryTitle(String categoryTitle);

    List<ProductSmallDTO> findAllProductsByCityTitle(String cityTitle);

    List<ProductSmallDTO> findAllProductsByCityTitleAndReservationDate(String cityTitle, Date checkIn, Date checkOut);

}
