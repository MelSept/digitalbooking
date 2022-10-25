package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.entity.Category;
import com.digitalbooking.apilodgings.exception.request.BadRequestException;
import com.digitalbooking.apilodgings.exception.request.NotFoundException;
import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.response.ResponseCategoryList;
import com.digitalbooking.apilodgings.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("CategoryServiceImpl") ICategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category requestCategory, @RequestHeader HttpHeaders requestHeaders) throws BadRequestException {
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json");

        Category categoryCreated = categoryService.createCategory(requestCategory);

        return new ResponseEntity<>(categoryCreated, headers, HttpStatus.CREATED);
    }

    @GetMapping("/type/{title}")
    public ResponseEntity<Optional<Category>> findCategoryByTitle(@PathVariable String title) throws NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Optional<Category> categoryFound = categoryService.findCategoryByTitle(title);

        return new ResponseEntity<>(categoryFound, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> findCategoryByTitle(@PathVariable Integer id) throws NotFoundException, BadRequestException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Optional<Category> categoryFound = categoryService.findCategoryById(id);

        return new ResponseEntity<>(categoryFound, headers, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategoryById(@RequestBody Category requestCategory) throws BadRequestException, NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Category categoryUpdated = categoryService.updateCategory(requestCategory);

        return new ResponseEntity<>(categoryUpdated, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCategoryById(@PathVariable Integer id) throws BadRequestException, NotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        Response categoryDeleted = categoryService.deleteCategoryById(id);

        return new ResponseEntity<>(categoryDeleted, headers, HttpStatus.OK);
    }

    @GetMapping(path = {"","/"})
    public ResponseEntity<ResponseCategoryList> findAllCategories() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        ResponseCategoryList categoriesFound = categoryService.findAllCategories();

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }
}
