package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.entity.Place;
import com.digitalbooking.apilodgings.service.place.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/place")
public class PlaceController {

    private final IPlaceService placeService;

    @Autowired
    public PlaceController(IPlaceService placeService) {
        this.placeService = placeService;
    }


    @GetMapping(path = {"/category/{categoryTitle}"})
    public ResponseEntity<List<Place>> findAllPlacesByCategoryTitle(@PathVariable String categoryTitle) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Place> categoriesFound = placeService.findAllPlacesByCategoryTitle(categoryTitle);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }


    @GetMapping(path = {"", " ", "/"})
    public ResponseEntity<List<Place>> findAllPlaces() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Place> categoriesFound = placeService.findAllPlaces();

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }
}
