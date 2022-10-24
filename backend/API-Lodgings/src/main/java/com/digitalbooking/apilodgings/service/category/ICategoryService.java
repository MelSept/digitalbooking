package com.digitalbooking.apilodgings.service.category;

import com.digitalbooking.apilodgings.entity.Category;
import com.digitalbooking.apilodgings.exception.request.BadRequestException;
import com.digitalbooking.apilodgings.exception.request.NotFoundException;
import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.response.ResponseCategoryList;

import java.util.Optional;

public interface ICategoryService {

    Category createCategory(Category category) throws BadRequestException;

    Optional<Category> findCategoryById(Integer id) throws NotFoundException, BadRequestException;

    Optional<Category> findCategoryByTitle(String title) throws NotFoundException;

    Category updateCategory(Category category) throws BadRequestException, NotFoundException;

    Response deleteCategoryById(Integer id) throws NotFoundException, BadRequestException;

    ResponseCategoryList findAllCategories();
}
