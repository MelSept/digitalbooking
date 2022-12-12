package com.digitalbooking.apilodgings.service.feature;

import com.digitalbooking.apilodgings.dto.feature.FeatureDTO;
import com.digitalbooking.apilodgings.entity.Feature;
import com.digitalbooking.apilodgings.repository.IFeatureRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "FeatureServiceImpl")
public class FeatureServiceImpl implements IFeatureService {

    IFeatureRepository featureRepository;
    ObjectMapper mapper;


    @Autowired
    public FeatureServiceImpl(IFeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
        mapper = new ObjectMapper();
    }


    @Override
    public FeatureDTO createFeature(FeatureDTO feature) {
        return null;
    }

    @Override
    public List<FeatureDTO> findFeaturesByProductId(Integer productId) {
        return null;
    }

    @Override
    public List<FeatureDTO> findAll() {

        List<Feature> features = featureRepository.findAll();
        List<FeatureDTO> featureDTOS = new ArrayList<>();

        for (Feature feature : features)
        {
            featureDTOS.add(mapper.convertValue(feature, FeatureDTO.class));
        }

            return featureDTOS;
    }
}
