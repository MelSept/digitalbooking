package com.digitalbooking.apilodgings.service.city;

import com.digitalbooking.apilodgings.dto.CityDTO;
import com.digitalbooking.apilodgings.entity.City;
import com.digitalbooking.apilodgings.repository.ICityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CityServiceImpl")
public class CityServiceImpl implements ICityService{

    private final ICityRepository cityRepository;
    private final ObjectMapper mapper;


    @Autowired
    public CityServiceImpl(@Qualifier("ICityRepository") ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
        mapper = new ObjectMapper();
    }


    @Override
    public List<CityDTO> findAllCities() {

        List<City> citiesFound = cityRepository.findAll();
        List<CityDTO> citiesDTOFound = new ArrayList<>();

        for (City city : citiesFound) {
            CityDTO cityDTO = mapper.convertValue(city, CityDTO.class);
            citiesDTOFound.add(cityDTO);
        }

        return citiesDTOFound;
    }
}
