package com.digitalbooking.apilodgings.service.productFeature;

import com.digitalbooking.apilodgings.dto.FeatureDTO;
import com.digitalbooking.apilodgings.entity.Feature;
import com.digitalbooking.apilodgings.entity.ProductFeatures;
import com.digitalbooking.apilodgings.repository.IProductFeaturesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductFeatureService implements IProductFeaturesService {

    IProductFeaturesRepository productFeaturesRepository;
    ObjectMapper mapper;

    @Autowired
    public ProductFeatureService(IProductFeaturesRepository productFeaturesRepository) {
        this.productFeaturesRepository = productFeaturesRepository;
        mapper = new ObjectMapper();
    }


    @Override
    public List<FeatureDTO> findAllFeaturesByProductId(Integer productId) {

        List<ProductFeatures> productFeaturesFound = productFeaturesRepository.findAllByProduct_Id(productId);
        List<FeatureDTO> featuresDTO = new ArrayList<>();

        for (ProductFeatures productFeatures : productFeaturesFound) {
            featuresDTO.add(mapper.convertValue(productFeatures.getFeature(), FeatureDTO.class));
        }

        return featuresDTO;
    }
}
