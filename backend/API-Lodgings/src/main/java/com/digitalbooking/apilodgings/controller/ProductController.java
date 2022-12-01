package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.dto.product.ProductDTO;
import com.digitalbooking.apilodgings.dto.product.ProductMiniDTO;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.service.product.IProductService;
import com.digitalbooking.apilodgings.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    // TODO: Finish implementation
    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productRequestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        ProductDTO productFound = productService.createProduct(productRequestDTO);

        return new ResponseEntity<>(productFound, headers, HttpStatus.OK);
    }


    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Integer id) throws NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        ProductDTO productFound = productService.findProductById(id);

        return new ResponseEntity<>(productFound, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"/category/{title}"})
    public ResponseEntity<List<ProductMiniDTO>> findAllProductsByCategoryTitle(@PathVariable String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductMiniDTO> response = productService.findAllProductsByCategoryTitle(title);

        HttpStatus status = response.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(response, headers, status);
    }

    @GetMapping(path = {"/city/{title}"})
    public ResponseEntity<List<ProductMiniDTO>> findAllProductsByCityName(@PathVariable String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductMiniDTO> categoriesFound = productService.findAllProductsByCityTitle(title);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"/"})
    public ResponseEntity<List<ProductMiniDTO>> findAllProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductMiniDTO> categoriesFound = productService.findAllProducts();

        HttpStatus status = categoriesFound.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(categoriesFound, headers, status);
    }

    @GetMapping(path = {"/random/"})
    public ResponseEntity<List<ProductMiniDTO>> findAllRandomProducts(@RequestParam(required = false) Integer quantity) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductMiniDTO> categoriesFound = productService.findAllProductsRandom(quantity);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"/filter"})
    public ResponseEntity<List<ProductMiniDTO>> findAllProductsByCityTitleAndReservationDate(
            @RequestParam String city,
            @RequestParam String checkIn,
            @RequestParam String checkOut) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var checkInParse = DateUtils.asDate(LocalDate.parse(checkIn, DateTimeFormatter.ISO_DATE));
        var checkOutParse = DateUtils.asDate(LocalDate.parse(checkOut, DateTimeFormatter.ISO_DATE));

        List<ProductMiniDTO> categoriesFound =
                productService.findAllProductsByCityTitleAndReservationDate(city, checkInParse, checkOutParse);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }

}
