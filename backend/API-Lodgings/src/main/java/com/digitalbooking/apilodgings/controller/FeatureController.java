package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.dto.feature.FeatureDTO;
import com.digitalbooking.apilodgings.service.feature.IFeatureService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/feature")
@Tag(name = "Feature", description = "Endpoint to management features")
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Bearer Authentication",
        description = "Use JWT for authenticate",
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class FeatureController {


    private final IFeatureService featureService;

    @Autowired
    public FeatureController(IFeatureService featureService) {
        this.featureService = featureService;
    }


    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<FeatureDTO>> findAllFeatures() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<FeatureDTO> featureDTOS = featureService.findAll();

        HttpStatus status = featureDTOS.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT;

        return new ResponseEntity<>(featureDTOS, headers, status);
    }
}
