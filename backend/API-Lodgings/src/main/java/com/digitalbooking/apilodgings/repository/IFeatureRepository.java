package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface IFeatureRepository extends JpaRepository<Feature, Integer> {

    Optional<Feature> findByTitleIgnoreCase(String title);
}