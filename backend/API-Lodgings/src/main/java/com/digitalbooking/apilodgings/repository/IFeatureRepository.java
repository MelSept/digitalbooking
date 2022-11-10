package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeatureRepository extends JpaRepository<Feature, Integer> {
}