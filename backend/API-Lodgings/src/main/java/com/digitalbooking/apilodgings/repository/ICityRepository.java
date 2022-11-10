package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ICityRepository")
public interface ICityRepository extends JpaRepository<City, Integer> {

}