package com.digitalbooking.apilodgings.service.category;

import com.digitalbooking.apilodgings.dto.CategoryDTO;
import com.digitalbooking.apilodgings.exception.BadRequestException;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.response.category.ResponseCategoryList;

public interface ICategoryService {

    CategoryDTO createCategory(CategoryDTO category) throws BadRequestException;

    CategoryDTO findCategoryById(Integer id) throws NotFoundException, BadRequestException;

    CategoryDTO findCategoryByTitle(String title) throws NotFoundException;

    CategoryDTO updateCategory(CategoryDTO category) throws BadRequestException, NotFoundException;

    Response deleteCategoryById(Integer id) throws NotFoundException, BadRequestException;

    ResponseCategoryList findAllCategories();
}
