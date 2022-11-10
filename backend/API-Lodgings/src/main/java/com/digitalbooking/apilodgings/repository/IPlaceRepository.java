package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlaceRepository extends JpaRepository<Place, Integer> {

    List<Place> findAllByCategory_TitleIgnoreCase(String categoryTitle);
}