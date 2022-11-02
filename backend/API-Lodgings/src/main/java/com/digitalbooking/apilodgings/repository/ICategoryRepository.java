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
    @Query("select c from Category c where c.id = ?1")
    @Override
    Optional<Category> findById(Integer integer);

    /**
     * Find Category in DB by "Category Title"
     */
    @Query("select c from Category c where upper(c.title) = upper(?1)")
    Optional<Category> findByTitleIgnoreCase(String title);


    @Query("select c from Category c where c.deleted = false")
    List<Category> findAllByDeletedIsFalse();
}
