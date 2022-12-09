package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.dto.product.CreateProductDTO;
import com.digitalbooking.apilodgings.dto.product.ProductDTO;
import com.digitalbooking.apilodgings.dto.product.ProductSmallDTO;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.service.product.IProductService;
import com.digitalbooking.apilodgings.utility.DateUtils;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/product")
@Tag(name = "Product", description = "Endpoint to management products")
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Bearer Authentication",
        description = "Use JWT for authenticate",
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody CreateProductDTO createProductDTO) throws NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        ProductDTO productFound = productService.createProduct(createProductDTO);

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
    public ResponseEntity<List<ProductSmallDTO>> findAllProductsByCategoryTitle(@PathVariable String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductSmallDTO> response = productService.findAllProductsByCategoryTitle(title);

        HttpStatus status = response.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(response, headers, status);
    }

    @GetMapping(path = {"/city/{title}"})
    public ResponseEntity<List<ProductSmallDTO>> findAllProductsByCityName(@PathVariable String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductSmallDTO> categoriesFound = productService.findAllProductsByCityTitle(title);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<ProductSmallDTO>> findAllProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductSmallDTO> categoriesFound = productService.findAllProducts();

        HttpStatus status = categoriesFound.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(categoriesFound, headers, status);
    }

    @GetMapping(path = {"/random/"})
    public ResponseEntity<List<ProductSmallDTO>> findAllRandomProducts(@RequestParam(required = false) Integer quantity) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<ProductSmallDTO> categoriesFound = productService.findAllProductsRandom(quantity);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"/filter"})
    public ResponseEntity<List<ProductSmallDTO>> findAllProductsByCityTitleAndReservationDate(
            @RequestParam String city,
            @RequestParam String checkIn,
            @RequestParam String checkOut) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var checkInParse = DateUtils.asDate(LocalDate.parse(checkIn, DateTimeFormatter.ISO_DATE));
        var checkOutParse = DateUtils.asDate(LocalDate.parse(checkOut, DateTimeFormatter.ISO_DATE));

        List<ProductSmallDTO> categoriesFound =
                productService.findAllProductsByCityTitleAndReservationDate(city, checkInParse, checkOutParse);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Response> deleteById(@PathVariable Integer id) throws NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        productService.deleteById(id);
        Response response = new Response(String.format("Product with id: %s was deleted", id));

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
