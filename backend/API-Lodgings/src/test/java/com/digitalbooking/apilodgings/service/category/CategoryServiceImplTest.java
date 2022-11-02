package com.digitalbooking.apilodgings.service.category;

import com.digitalbooking.apilodgings.dto.CategoryDTO;
import com.digitalbooking.apilodgings.exception.request.BadRequestException;
import com.digitalbooking.apilodgings.exception.request.NotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        CategoryDTO actual = new CategoryDTO();
        actual.setTitle("Hotel");
        actual.setDescription("Alojamiento y más");
        actual.setImageUrl("https://images.unsplash.com/photo-1565629196891-2ddb37c9e9fc?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80");
        CategoryDTO expected = new CategoryDTO();
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
        List<CategoryDTO> actual;
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
        CategoryDTO actual = null;
        // Act
        try {
            actual = categoryService.findCategoryByTitle("hotel");
        } catch (NotFoundException ignored) {
        }
        // Message
        String message;
        // Assert
        assertNotNull(actual);
    }

    @Test
    @Order(4)
    public void findCategoryById__WithStoredRecord__MustBeReturnCategoryStoredInDB() {
        // Arrange
        CategoryDTO actual = null;
        // Act
        try {
            actual = categoryService.findCategoryById(1);
        } catch (NotFoundException | BadRequestException ignored) {
        }
        // Message
        String message;
        // Assert
        assertNotNull(actual);
    }

    @Test
    @Order(5)
    public void updateCategory__WithStoredRecord__MustBeReturnUpdatedCategoryStoredInDB() {
        // Arrange
        CategoryDTO actual = null;
        CategoryDTO expected = new CategoryDTO();
        // Act
        try {
            actual = categoryService.findCategoryById(1);
            if (actual != null) {
                actual.setDescription("Alojamiento");
                expected = categoryService.updateCategory(actual);
            }
        } catch (NotFoundException | BadRequestException ignored) {
        }
        // Message
        String message;
        // Assert
        if (actual != null) {
            assertEquals(actual.getTitle(), expected.getTitle());
        }
    }

    @Test
    @Order(6)
    public void deleteCategoryById__WithStoredRecord__MustBeReturnCategoryDeleted() {
        // Arrange
        CategoryDTO expected = new CategoryDTO();
        // Act
        try {
            categoryService.deleteCategoryById(1);
            expected = categoryService.findCategoryById(1);
        } catch (NotFoundException | BadRequestException ignored) {
        }
        // Message
        String message;
        // Assert
        assertNull(expected.getId());
    }
}