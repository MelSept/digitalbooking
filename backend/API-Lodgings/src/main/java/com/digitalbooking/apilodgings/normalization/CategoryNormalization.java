package com.digitalbooking.apilodgings.normalization;

import com.digitalbooking.apilodgings.entity.Category;

public class CategoryNormalization {

    public static Category Normalize(Category category) {

        Category categoryNormalize = new Category();
        categoryNormalize.setId(category.getId());
        categoryNormalize.setTitle(category.getTitle().trim().toLowerCase());
        categoryNormalize.setDescription(category.getDescription().trim().toLowerCase());
        categoryNormalize.setImageUrl(category.getImageUrl().trim().toLowerCase());
        categoryNormalize.setDeleted(category.isDeleted());
        return categoryNormalize;
    }
}
