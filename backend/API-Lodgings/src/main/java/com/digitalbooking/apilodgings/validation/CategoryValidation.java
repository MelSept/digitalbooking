package com.digitalbooking.apilodgings.validation;

import com.digitalbooking.apilodgings.entity.Category;
import com.digitalbooking.apilodgings.exception.request.BadRequestException;
import com.digitalbooking.apilodgings.response.ResponseError;

import java.util.Objects;

public class CategoryValidation {

    public static void ValidateCategoryCreation(Category category) throws BadRequestException {
        validateId(category.getId());
        validateTitle(category.getTitle());
        validateDescription(category.getDescription());
        validateImageUrl(category.getImageUrl());
    }

    public static void validateId(Integer id) throws BadRequestException {
        if (id != null) {

            ResponseError responseError = new ResponseError("Invalid Operation. See 'hints' to know cause problem.");
            responseError.addHint("Don't use field 'id' in the request body.");
            responseError.addHint("Field 'id' must not be present in the request body.");

            throw new BadRequestException(responseError);
        }
    }

    public static void validateTitle(String title) throws BadRequestException {
        if (title == null || title.isBlank()) {

            ResponseError responseError = new ResponseError("Invalid Operation. See 'hints' to know cause problem.");
            responseError.addHint("Field 'title' is empty or null.");
            responseError.addHint("Field 'title' is not present in request body.");

            throw new BadRequestException(responseError);
        }
    }

    public static void validateDescription(String description) throws BadRequestException {
        if (description == null || description.isBlank()) {

            ResponseError responseError = new ResponseError("Invalid Operation. See hints to know cause problem.");
            responseError.addHint("Field 'description' is empty or null.");
            responseError.addHint("Field 'description' is not present in request body.");

            throw new BadRequestException(responseError);
        }
    }

    public static void validateImageUrl(String imageUrl) throws BadRequestException {
        if (imageUrl == null || imageUrl.isBlank()) {

            ResponseError responseError = new ResponseError("Invalid Operation. See hints to know cause problem.");
            responseError.addHint("Field 'imageUrl' is empty or null.");
            responseError.addHint("Field 'imageUrl' is not present in request body.");

            throw new BadRequestException(responseError);
        }
    }


    public static void cannotCreateCategory(Category category) throws BadRequestException {

        ResponseError responseError = new ResponseError("Invalid Operation: category creation");
        responseError.addHint(String.format("Category '%s' with id: '%s' already exist.", category.getTitle(), category.getId()));

        throw new BadRequestException(responseError);
    }
    public static void cannotUpdateCategory(Category categoryFoundById, Category categoryFoundByTitle) throws BadRequestException {

        if (!Objects.equals(categoryFoundById.getId(), categoryFoundByTitle.getId())) {
            ResponseError responseError = new ResponseError("Invalid Operation: update category");
            responseError.addHint(String.format("Category '%s' with id: '%s' already exist.",
                    categoryFoundByTitle.getTitle(), categoryFoundByTitle.getId()));

            throw new BadRequestException(responseError);
        }
    }
}
