package com.digitalbooking.apilodgings.service.category;

import com.digitalbooking.apilodgings.entity.Category;
import com.digitalbooking.apilodgings.exception.request.BadRequestException;
import com.digitalbooking.apilodgings.exception.request.NotFoundException;
import com.digitalbooking.apilodgings.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CategoryServiceImplTest {

    private final ICategoryService categoryService;

    private final int idLastRecord = 5;


    CategoryServiceImplTest(@Qualifier("CategoryServiceImpl") ICategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Test
    @Order(1)
    public void createCategory__RecordNewCategory__MustBeReturnCategoryStoredInDB() {
        // Arrange
        Category actual = new Category(
                "Hotel",
                "Alojamiento y más",
                "https://images.unsplash.com/photo-1565629196891-2ddb37c9e9fc?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80");
        Category expected = new Category();
        // Act
        try {
            expected = categoryService.createCategory(actual);
        } catch (BadRequestException ignored) {
        }
        // Message
        String message;
        // Assert
        assertTrue(expected.getId() != 0);
    }

    @Test
    @Order(2)
    public void findAllCategories__WithStoredRecords__MustBeReturnGreaterThan0() {
        // Arrange
        List<Category> actual;
        // Act
        actual = categoryService.findAllCategories().getCategories();
        // Message
        String message;
        // Assert
        assertTrue(actual.size() > 0);
    }

    @Test
    @Order(3)
    public void findCategoryByTitle__WithStoredRecord__MustBeReturnCategoryStoredInDB() {
        // Arrange
        Optional<Category> actual = Optional.empty();
        // Act
        try {
            actual = categoryService.findCategoryByTitle("hotel");
        } catch (NotFoundException ignored) {
        }
        // Message
        String message;
        // Assert
        assertTrue(actual.isPresent());
    }

    @Test
    @Order(4)
    public void findCategoryById__WithStoredRecord__MustBeReturnCategoryStoredInDB() {
        // Arrange
        Optional<Category> actual = Optional.empty();
        // Act
        try {
            actual = categoryService.findCategoryById(1);
        } catch (NotFoundException | BadRequestException ignored) {
        }
        // Message
        String message;
        // Assert
        assertTrue(actual.isPresent());
    }

    @Test
    @Order(5)
    public void updateCategory__WithStoredRecord__MustBeReturnUpdatedCategoryStoredInDB() {
        // Arrange
        Optional<Category> actual = Optional.empty();
        Category expected = new Category();
        // Act
        try {
            actual = categoryService.findCategoryById(1);
            if (actual.isPresent()) {
                actual.get().setTitle("Apartamento");
                expected = categoryService.updateCategory(actual.get());
            }
        } catch (NotFoundException | BadRequestException ignored) {
        }
        // Message
        String message;
        // Assert
        if (actual.isPresent()) {
            assertEquals(actual.get().getTitle(), expected.getTitle());
        }
    }

    @Test
    @Order(6)
    public void deleteCategoryById__WithStoredRecord__MustBeReturnCategoryDeleted() {
        // Arrange
        Response actual = null;
        // Act
        try {
            actual = categoryService.deleteCategoryById(1);
        } catch (NotFoundException | BadRequestException ignored) {
        }
        // Message
        String message;
        // Assert
        assertNotNull(actual);
    }
}