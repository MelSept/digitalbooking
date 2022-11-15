package com.digitalbooking.apilodgings.service.place;

import com.digitalbooking.apilodgings.entity.Place;
import com.digitalbooking.apilodgings.repository.IPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "PlaceServiceImpl")
public class PlaceServiceImpl implements IPlaceService {

    private final IPlaceRepository placeRepository;


    @Autowired
    public PlaceServiceImpl(IPlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    @Override
    public List<Place> findAllPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public List<Place> findAllPlacesByCategoryTitle(String categoryTitle) {
        return placeRepository.findAllByCategory_TitleIgnoreCase(categoryTitle);
    }
}
