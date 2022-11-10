package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.dto.CategoryDTO;
import com.digitalbooking.apilodgings.exception.BadRequestException;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.response.category.ResponseCategoryList;
import com.digitalbooking.apilodgings.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("CategoryServiceImpl") ICategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO requestCategory) throws BadRequestException {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json");

        CategoryDTO categoryCreated = categoryService.createCategory(requestCategory);

        return new ResponseEntity<>(categoryCreated, headers, HttpStatus.CREATED);
    }

    @GetMapping("/name/{title}")
    public ResponseEntity<CategoryDTO> findCategoryByTitle(@PathVariable String title) throws NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        CategoryDTO categoryFound = categoryService.findCategoryByTitle(title);

        return new ResponseEntity<>(categoryFound, headers, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Integer id) throws NotFoundException, BadRequestException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        CategoryDTO categoryFound = categoryService.findCategoryById(id);

        return new ResponseEntity<>(categoryFound, headers, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategoryById(@RequestBody CategoryDTO requestCategory) throws BadRequestException, NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        CategoryDTO categoryUpdated = categoryService.updateCategory(requestCategory);

        return new ResponseEntity<>(categoryUpdated, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCategoryById(@PathVariable Integer id) throws BadRequestException, NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Response categoryDeleted = categoryService.deleteCategoryById(id);

        return new ResponseEntity<>(categoryDeleted, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"", " ", "/"})
    public ResponseEntity<ResponseCategoryList> findAllCategories() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        ResponseCategoryList categoriesFound = categoryService.findAllCategories();

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }
}
