package com.digitalbooking.apilodgings.service.product;

import com.digitalbooking.apilodgings.dto.FeatureDTO;
import com.digitalbooking.apilodgings.dto.ImageDTO;
import com.digitalbooking.apilodgings.dto.ProductDTO;
import com.digitalbooking.apilodgings.entity.Product;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.repository.IProductRepository;
import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.service.image.IImageService;
import com.digitalbooking.apilodgings.service.productFeature.IProductFeaturesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductFeaturesService productFeaturesService;
    private final IProductRepository productRepository;
    private final IImageService imageService;
    private final ObjectMapper mapper;

    public ProductServiceImpl(IProductFeaturesService productFeaturesService, IProductRepository productRepository, IImageService imageService) {
        this.productFeaturesService = productFeaturesService;
        this.productRepository = productRepository;
        this.imageService = imageService;
        this.mapper = new ObjectMapper();
    }


    @Override
    public ProductDTO findProductById(Integer productId) throws NotFoundException {
        Product product = productRepository.findById(productId).orElse(null);

        if (product == null || product.getId() == null) {
            Response responseException = new Response(String.format("Not found product with id: '%s'", productId));
            throw new NotFoundException(responseException);
        }

        List<ImageDTO> imagesDTOFound = imageService.findAllImagesByProductId(product.getId());
        List<FeatureDTO> featuresDTOFound = productFeaturesService.findAllFeaturesByProductId(product.getId());

        ProductDTO productDTO = mapper.convertValue(product, ProductDTO.class);

        productDTO.setImages(imagesDTOFound);
        productDTO.setFeatures(featuresDTOFound);

        return productDTO;
    }

    @Override
    public List<ProductDTO> findAllProducts() {

        List<Product> productsFound = productRepository.findAll();
        return getProductDTOS(productsFound);
    }

    @Override
    public List<ProductDTO> findAllProductsByCategoryTitle(String categoryTitle) {
        List<Product> productsFound = productRepository.findAllByPlace_Category_TitleIgnoreCase(categoryTitle);
        return getProductDTOS(productsFound);
    }

    @Override
    public List<ProductDTO> findAllProductsByCityTitle(String cityTitle) {
        List<Product> productsFound = productRepository.findAllByPlace_City_TitleIgnoreCase(cityTitle);
        return getProductDTOS(productsFound);
    }


    private List<ProductDTO> getProductDTOS(List<Product> productsFound) {
        List<ProductDTO> productsDTO = new ArrayList<>();


        for (Product product : productsFound) {

            List<ImageDTO> imagesDTOFound = imageService.findAllImagesByProductId(product.getId());
            List<FeatureDTO> featuresDTOFound = productFeaturesService.findAllFeaturesByProductId(product.getId());

            ProductDTO productDTO = mapper.convertValue(product, ProductDTO.class);

            productDTO.setImages(imagesDTOFound);
            productDTO.setFeatures(featuresDTOFound);

            productsDTO.add(productDTO);
        }

        return productsDTO;
    }
}
