package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Query("""
            select p from Product p
            where upper(p.place.category.title) = upper(?1) and upper(p.place.city.title) = upper(?2) and p.deleted = false""")
    List<Product> findAllByCategoryTitleAndCityTitleAndDeletedIsFalse(String categoryTitle, String cityTitle);



    int countByPlace_Category_TitleIgnoreCase(String title);


}