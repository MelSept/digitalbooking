package com.digitalbooking.apilodgings.service.city;

import com.digitalbooking.apilodgings.dto.city.CityDTO;

import java.util.List;

public interface ICityService {

    List<CityDTO> findAllCities();
}
