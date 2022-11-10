package com.digitalbooking.apilodgings.service.image;

import com.digitalbooking.apilodgings.dto.ImageDTO;
import com.digitalbooking.apilodgings.entity.Image;
import com.digitalbooking.apilodgings.repository.IImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

        List<Image> imagesFound = imageRepository.findAllByProduct_Id(productId);
        List<ImageDTO> imagesDTOFound = new ArrayList<>();

        for (Image image : imagesFound) {
            ImageDTO imageDTO = mapper.convertValue(image, ImageDTO.class);
            imagesDTOFound.add(imageDTO);
        }

        return imagesDTOFound;
    }
}
