package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.ProductFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface IProductFeaturesRepository extends JpaRepository<ProductFeatures, Integer> {
    List<ProductFeatures> findAllByProduct_Id(Integer productId);
}