package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    /**
     * Find Category in DB by ID
     */
    @Override
    Optional<Category> findById(Integer integer);

    /**
     * Find Category in DB by "Category Title"
     */
    Optional<Category> findByTitleIgnoreCase(String title);


    List<Category> findAllByDeletedIsFalse();
}
