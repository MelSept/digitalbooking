package com.digitalbooking.apilodgings.service.feature;

import com.digitalbooking.apilodgings.dto.feature.FeatureDTO;

import java.util.List;

public interface IFeatureService {

    FeatureDTO createFeature(FeatureDTO feature);
    List<FeatureDTO> findFeaturesByProductId(Integer productId);

    List<FeatureDTO> findAll();
}
