package com.digitalbooking.apilodgings.service.productFeature;

import com.digitalbooking.apilodgings.dto.FeatureDTO;

import java.util.List;

public interface IProductFeaturesService {

    List<FeatureDTO> findAllFeaturesByProductId(Integer productId);

}
