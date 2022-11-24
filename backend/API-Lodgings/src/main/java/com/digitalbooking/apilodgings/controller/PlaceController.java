package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.entity.Place;
import com.digitalbooking.apilodgings.service.place.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/place")
public class PlaceController {

    private final IPlaceService placeService;

    @Autowired
    public PlaceController(IPlaceService placeService) {
        this.placeService = placeService;
    }


    @GetMapping(path = {"/category/{title}"})
    public ResponseEntity<List<Place>> findAllPlacesByCategoryTitle(@PathVariable String title) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Place> categoriesFound = placeService.findAllPlacesByCategoryTitle(title);

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }


    @GetMapping(path = {"/"})
    public ResponseEntity<List<Place>> findAllPlaces() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<Place> categoriesFound = placeService.findAllPlaces();

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }
}
