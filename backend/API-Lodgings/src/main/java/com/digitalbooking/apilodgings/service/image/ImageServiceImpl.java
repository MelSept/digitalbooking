package com.digitalbooking.apilodgings.service.image;

import com.digitalbooking.apilodgings.dto.Image.ImageDTO;
import com.digitalbooking.apilodgings.repository.IImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ImageServiceImpl")
public class ImageServiceImpl implements IImageService {

    IImageRepository imageRepository;
    ObjectMapper mapper;


    @Autowired
    public ImageServiceImpl(IImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        mapper = new ObjectMapper();
    }


    @Override
    public List<ImageDTO> findAllImages() {
        return null;
    }


    @Override
    public List<ImageDTO> findAllImagesByProductId(Integer productId) {
        return null;
    }
}
