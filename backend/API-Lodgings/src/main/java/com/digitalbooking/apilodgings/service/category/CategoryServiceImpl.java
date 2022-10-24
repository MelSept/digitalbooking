package com.digitalbooking.apilodgings.service.category;

import com.digitalbooking.apilodgings.entity.Category;
import com.digitalbooking.apilodgings.exception.request.BadRequestException;
import com.digitalbooking.apilodgings.exception.request.NotFoundException;
import com.digitalbooking.apilodgings.repository.ICategoryRepository;
import com.digitalbooking.apilodgings.response.ResponseCategoryList;
import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.response.ResponseError;
import com.digitalbooking.apilodgings.validations.CategoryServiceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class to work with CRUD
 */
@Service("CategoryServiceImpl")
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category createCategory(Category category) throws BadRequestException {
        CategoryServiceValidation.ValidateCategoryCreation(category);

        Optional<Category> categoryFound = categoryRepository.findByTitleIgnoreCase(category.getTitle());

        if (categoryFound.isPresent()) {
            CategoryServiceValidation.cannotCreateCategory(categoryFound.get());
        }

        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) throws NotFoundException, BadRequestException {
        if (id == null) {
            ResponseError responseError = new ResponseError("Invalid Operation. See 'hints' to know cause problem.");
            responseError.addHint("The 'id' path variable is empty or null");
            throw new BadRequestException(responseError);
        }

        Optional<Category> categoryFound = categoryRepository.findById(id);

        if (categoryFound.isEmpty()) {
            Response responseException = new Response(String.format("Not found category with id: '%s'", id));
            throw new NotFoundException(responseException);
        }

        return categoryFound;
    }

    @Override
    public Optional<Category> findCategoryByTitle(String title) throws NotFoundException {

        Optional<Category> categoryFound = categoryRepository.findByTitleIgnoreCase(title);

        if (categoryFound.isEmpty()) {
            Response responseException = new Response(String.format("Not found category with title: '%s'", title));
            throw new NotFoundException(responseException);
        }

        return categoryRepository.findByTitleIgnoreCase(title);
    }

    @Override
    public Category updateCategory(Category category) throws NotFoundException, BadRequestException {

        if (category.getId() == null) {
            ResponseError responseError = new ResponseError("Invalid Operation. See 'hints' to know cause problem.");
            responseError.addHint("The 'id' field is empty or null");
            responseError.addHint("The 'id' field is missing in the body of the request.");
            throw new BadRequestException(responseError);
        }

        Optional<Category> categoryFound = categoryRepository.findById(category.getId());

        if (categoryFound.isEmpty()) {
            Response responseException = new Response(String.format("Not found category with id: '%s'", category.getId()));
            throw new NotFoundException(responseException);
        }

        return categoryRepository.save(category);
    }

    @Override
    public Response deleteCategoryById(Integer id) throws NotFoundException, BadRequestException {

        if (id == null) {
            ResponseError responseError = new ResponseError("Invalid Operation. See 'hints' to know cause problem.");
            responseError.addHint("The 'id' path variable is empty or null");
            throw new BadRequestException(responseError);
        }

        Optional<Category> categoryFound = categoryRepository.findById(id);

        if (categoryFound.isEmpty()) {
            Response responseException = new Response(String.format("Not found category with id: '%s'", id));
            throw new NotFoundException(responseException);
        } else {
            categoryRepository.deleteById(id);
        }

        return new Response(String.format("Category '%s' deleted with id: '%s'", categoryFound.get().getTitle(), id));
    }

    @Override
    public ResponseCategoryList findAllCategories() {
        List<Category> categoriesFound = categoryRepository.findAll();
        int count = categoriesFound.size();

        return new ResponseCategoryList(count, categoriesFound);
    }
}
