package com.digitalbooking.apilodgings.service.category;

import com.digitalbooking.apilodgings.dto.CategoryDTO;
import com.digitalbooking.apilodgings.entity.Category;
import com.digitalbooking.apilodgings.exception.request.BadRequestException;
import com.digitalbooking.apilodgings.exception.request.NotFoundException;
import com.digitalbooking.apilodgings.normalization.CategoryNormalization;
import com.digitalbooking.apilodgings.repository.ICategoryRepository;
import com.digitalbooking.apilodgings.response.category.ResponseCategoryList;
import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.response.ResponseError;
import com.digitalbooking.apilodgings.validation.CategoryValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class to work with CRUD
 */
@Service("CategoryServiceImpl")
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final ObjectMapper mapper;

    @Autowired
    public CategoryServiceImpl(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        mapper = new ObjectMapper();
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws BadRequestException {

        Category category = mapper.convertValue(categoryDTO, Category.class);

        CategoryValidation.ValidateCategoryCreation(category);

        Optional<Category> categoryFound = categoryRepository.findByTitleIgnoreCase(category.getTitle());
        Category categoryToSaved = new Category();

        if (categoryFound.isPresent() && categoryFound.get().isDeleted()) {
            categoryToSaved.setId(categoryFound.get().getId());
            categoryToSaved.setTitle(categoryFound.get().getTitle());
            categoryToSaved.setDescription(category.getDescription());
            categoryToSaved.setImageUrl(category.getImageUrl());
            categoryToSaved.setDeleted(false);

            categoryToSaved = CategoryNormalization.Normalize(categoryToSaved);
            categoryToSaved = categoryRepository.save(categoryToSaved);

            return mapper.convertValue(categoryToSaved, CategoryDTO.class);
        }

        if (categoryFound.isPresent() && !categoryFound.get().isDeleted()) {
            CategoryValidation.cannotCreateCategory(categoryFound.get());
        }

        if (categoryFound.isEmpty()) {
            category = CategoryNormalization.Normalize(category);
            categoryToSaved = categoryRepository.save(category);
        }

        return mapper.convertValue(categoryToSaved, CategoryDTO.class);
    }

    @Override
    public CategoryDTO findCategoryById(Integer id) throws NotFoundException, BadRequestException {

        if (id == null) {
            ResponseError responseError = new ResponseError("Invalid Operation. See 'hints' to know cause problem.");
            responseError.addHint("The 'id' path variable is empty or null");
            throw new BadRequestException(responseError);
        }

        Optional<Category> categoryFound = categoryRepository.findById(id);

        if (categoryFound.isEmpty() || categoryFound.get().isDeleted()) {
            Response responseException = new Response(String.format("Not found category with id: '%s'", id));
            throw new NotFoundException(responseException);
        }

        return mapper.convertValue(categoryFound.get(), CategoryDTO.class);
    }

    @Override
    public CategoryDTO findCategoryByTitle(String title) throws NotFoundException {

        Optional<Category> categoryFound = categoryRepository.findByTitleIgnoreCase(title);

        if (categoryFound.isEmpty() || categoryFound.get().isDeleted()) {
            Response responseException = new Response(String.format("Not found category with title: '%s'", title));
            throw new NotFoundException(responseException);
        }

        return mapper.convertValue(categoryFound.get(), CategoryDTO.class);
    }

    // TODO: Validate category fields
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) throws NotFoundException, BadRequestException {

        Category category = mapper.convertValue(categoryDTO, Category.class);

        if (category.getId() == null) {
            ResponseError responseError = new ResponseError("Invalid Operation. See 'hints' to know cause problem.");
            responseError.addHint("The 'id' field is empty or null");
            responseError.addHint("The 'id' field is missing in the body of the request.");
            throw new BadRequestException(responseError);
        }

        Category categoryFoundById = categoryRepository.findById(category.getId()).orElse(null);
        Category categoryFoundByTitle = categoryRepository.findByTitleIgnoreCase(category.getTitle()).orElse(null);

        Category categorySaved = new Category();

        if (categoryFoundById == null || categoryFoundById.isDeleted()) {
            Response responseException = new Response(String.format("Not found category with id: '%s'", category.getId()));
            throw new NotFoundException(responseException);
        }

        if (categoryFoundByTitle == null) {
            categorySaved.setId(category.getId());
            categorySaved.setTitle(category.getTitle());
            categorySaved.setDescription(category.getDescription());
            categorySaved.setImageUrl(category.getImageUrl());

            categorySaved = CategoryNormalization.Normalize(categorySaved);

            categorySaved = categoryRepository.save(categorySaved);

            return mapper.convertValue(categorySaved, CategoryDTO.class);
        }

        CategoryValidation.cannotUpdateCategory(categoryFoundById, categoryFoundByTitle);

        category = CategoryNormalization.Normalize(category);

        categorySaved = categoryRepository.save(category);

        return mapper.convertValue(categorySaved, CategoryDTO.class);
    }

    @Override
    public Response deleteCategoryById(Integer id) throws NotFoundException, BadRequestException {

        if (id == null) {
            ResponseError responseError = new ResponseError("Invalid Operation. See 'hints' to know cause problem.");
            responseError.addHint("The 'id' path variable is empty or null");
            throw new BadRequestException(responseError);
        }

        Optional<Category> categoryFound = categoryRepository.findById(id);

        if (categoryFound.isEmpty() || categoryFound.get().isDeleted()) {
            Response responseException = new Response(String.format("Not found category with id: '%s'", id));
            throw new NotFoundException(responseException);
        } else {
            Category categoryToDelete = categoryFound.get();
            categoryToDelete.setDeleted(true);
            categoryRepository.save(categoryToDelete);
        }

        return new Response(String.format("Category '%s' deleted with id: '%s'", categoryFound.get().getTitle(), id));
    }

    @Override
    public ResponseCategoryList findAllCategories() {
        List<Category> categoriesFound = categoryRepository.findAllByDeletedIsFalse();
        int count = categoriesFound.size();

        List<CategoryDTO> categoriesDTO = new ArrayList<>();

        for (Category categoryFound : categoriesFound) {
            categoriesDTO.add(mapper.convertValue(categoryFound, CategoryDTO.class));
        }

        return new ResponseCategoryList(count, categoriesDTO);
    }
}
