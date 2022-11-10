package com.digitalbooking.apilodgings.service.image;

import com.digitalbooking.apilodgings.dto.ImageDTO;

import java.util.List;

public interface IImageService {

    List<ImageDTO> findAllImages();
    List<ImageDTO> findAllImagesByProductId(Integer productId);

}
