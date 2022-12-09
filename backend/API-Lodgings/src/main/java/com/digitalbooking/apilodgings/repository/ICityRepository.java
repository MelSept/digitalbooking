package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("ICityRepository")
public interface ICityRepository extends JpaRepository<City, Integer> {

    Optional<City> findByTitleIgnoreCase(String cityTitle);
}