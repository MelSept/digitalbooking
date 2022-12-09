package com.digitalbooking.apilodgings.service.place;

import com.digitalbooking.apilodgings.dto.place.CreatePlaceDTO;
import com.digitalbooking.apilodgings.dto.place.PlaceDTO;
import com.digitalbooking.apilodgings.entity.Place;
import com.digitalbooking.apilodgings.exception.NotFoundException;

import java.util.List;

public interface IPlaceService {

    PlaceDTO createPlace(CreatePlaceDTO createPlaceDTO) throws NotFoundException;

    List<Place> findAllPlaces();

    List<Place> findAllPlacesByCategoryTitle(String categoryTitle);

}
