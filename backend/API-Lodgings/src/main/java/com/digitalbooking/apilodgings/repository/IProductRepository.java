package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Feature;
import com.digitalbooking.apilodgings.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByPlace_Category_TitleIgnoreCase(String categoryTitle);
    List<Product> findAllByPlace_City_TitleIgnoreCase(String cityTitle);
}