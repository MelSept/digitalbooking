package com.digitalbooking.apilodgings.service.place;

import com.digitalbooking.apilodgings.entity.Place;

import java.util.List;

public interface IPlaceService {

    List<Place> findAllPlaces();
    List<Place> findAllPlacesByCategoryTitle(String categoryTitle);

}
