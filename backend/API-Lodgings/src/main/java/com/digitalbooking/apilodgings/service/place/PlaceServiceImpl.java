package com.digitalbooking.apilodgings.service.place;

import com.digitalbooking.apilodgings.dto.place.CreatePlaceDTO;
import com.digitalbooking.apilodgings.dto.place.PlaceDTO;
import com.digitalbooking.apilodgings.entity.Category;
import com.digitalbooking.apilodgings.entity.City;
import com.digitalbooking.apilodgings.entity.Place;
import com.digitalbooking.apilodgings.entity.Policy;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.repository.ICategoryRepository;
import com.digitalbooking.apilodgings.repository.ICityRepository;
import com.digitalbooking.apilodgings.repository.IPlaceRepository;
import com.digitalbooking.apilodgings.repository.IPolicyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "PlaceServiceImpl")
public class PlaceServiceImpl implements IPlaceService {

    private final IPlaceRepository placeRepository;
    private final ICategoryRepository categoryRepository;
    private final ICityRepository cityRepository;
    private final IPolicyRepository policyRepository;

    private final ObjectMapper mapper;


    @Autowired
    public PlaceServiceImpl(IPlaceRepository placeRepository,
                            ICategoryRepository categoryRepository,
                            ICityRepository cityRepository,
                            IPolicyRepository policyRepository) {
        this.placeRepository = placeRepository;
        this.categoryRepository = categoryRepository;
        this.cityRepository = cityRepository;
        this.policyRepository = policyRepository;

        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }


    @Override
    public PlaceDTO createPlace(CreatePlaceDTO createPlaceDTO) throws NotFoundException {

        String categoryTitle = createPlaceDTO.getCategory();
        String cityTitle = createPlaceDTO.getCity();

        Policy policy = new Policy();
        policy.setNorm(createPlaceDTO.getPolicy().getNorm());
        policy.setSecurity(createPlaceDTO.getPolicy().getSecurity());
        policy.setCancellation(createPlaceDTO.getPolicy().getCancellation());

        Policy policySaved = policyRepository.save(policy);

        Category categoryFound =
                categoryRepository.findByTitleIgnoreCase(categoryTitle)
                        .orElseThrow(() -> new NotFoundException(String.format("Category with name: %s not found", categoryTitle)));
        City cityFound =
                cityRepository.findByTitleIgnoreCase(cityTitle)
                        .orElseThrow(() -> new NotFoundException(String.format("City with name: %s not found", cityTitle)));

        Place placeToSave = new Place();
        placeToSave.setTitle(createPlaceDTO.getTitle());
        placeToSave.setDescription(createPlaceDTO.getDescription());
        placeToSave.setAddress(createPlaceDTO.getAddress());
        placeToSave.setLatitude(createPlaceDTO.getLatitude() != 0 ? createPlaceDTO.getLatitude() : 0);
        placeToSave.setLongitude(createPlaceDTO.getLongitude() != 0 ? createPlaceDTO.getLongitude() : 0);
        placeToSave.setCategory(categoryFound);
        placeToSave.setCity(cityFound);
        placeToSave.setPolicy(policySaved);

        Place placeSaved = placeRepository.save(placeToSave);

        return mapper.convertValue(placeSaved, PlaceDTO.class);
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
