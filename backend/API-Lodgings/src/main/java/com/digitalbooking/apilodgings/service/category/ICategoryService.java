package com.digitalbooking.apilodgings.service.category;

import com.digitalbooking.apilodgings.dto.category.CategoryDTO;
import com.digitalbooking.apilodgings.dto.category.CategoryResponse;
import com.digitalbooking.apilodgings.exception.BadRequestException;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.response.Response;

import java.util.List;

public interface ICategoryService {

    CategoryDTO createCategory(CategoryDTO category) throws BadRequestException;

    CategoryDTO findCategoryById(Integer id) throws NotFoundException, BadRequestException;

    CategoryDTO findCategoryByTitle(String title) throws NotFoundException;

    CategoryDTO updateCategory(CategoryDTO category) throws BadRequestException, NotFoundException;

    Response deleteCategoryById(Integer id) throws NotFoundException, BadRequestException;

    List<CategoryResponse> findAllCategories();
}
