package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.dto.CityDTO;
import com.digitalbooking.apilodgings.service.city.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/city")
public class CityController {

    private final ICityService cityService;


    @Autowired
    public CityController(@Qualifier("CityServiceImpl") ICityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping(path = {"/"})
    public ResponseEntity<List<CityDTO>> findAllCategories() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<CityDTO> citiesFound = cityService.findAllCities();

        return new ResponseEntity<>(citiesFound, headers, HttpStatus.OK);
    }

}
