package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByIdAndDeletedIsFalse( Integer id);
    Optional<Product> findByTitleIgnoreCase(String title);
    Optional<Product> findByTitleIgnoreCaseAndDeletedIsFalse(String title);

    List<Product> findAllByDeletedIsFalse();
    List<Product> findAllByPlace_Category_TitleIgnoreCaseAndDeletedIsFalse(String categoryTitle);

    List<Product> findAllByPlace_City_TitleIgnoreCaseAndDeletedIsFalse(String cityTitle);
    int countByPlace_Category_TitleIgnoreCase(String title);


}