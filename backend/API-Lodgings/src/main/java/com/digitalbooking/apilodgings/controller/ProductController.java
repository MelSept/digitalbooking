package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.dto.ProductDTO;
import com.digitalbooking.apilodgings.entity.Product;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @GetMapping(path = {"/id/{productId}"})
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Integer productId) throws NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        ProductDTO productFound = productService.findProductById(productId);

        return new ResponseEntity<>(productFound, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"/category/{categoryTitle}"})
    public ResponseEntity<List<ProductDTO>> findAllProductsByCategoryTitle(@PathVariable String categoryTitle) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductDTO> categoriesFound = productService.findAllProductsByCategoryTitle(categoryTitle);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"/city/{cityName}"})
    public ResponseEntity<List<ProductDTO>> findAllProductsByCityName(@PathVariable String cityName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductDTO> categoriesFound = productService.findAllProductsByCityTitle(cityName);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"", " ", "/"})
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductDTO> categoriesFound = productService.findAllProducts();

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }

}
