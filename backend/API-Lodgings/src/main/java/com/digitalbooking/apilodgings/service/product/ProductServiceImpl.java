package com.digitalbooking.apilodgings.service.product;

import com.digitalbooking.apilodgings.dto.Image.CreateImageDTO;
import com.digitalbooking.apilodgings.dto.feature.CreateFeatureDTO;
import com.digitalbooking.apilodgings.dto.place.CreatePlaceDTO;
import com.digitalbooking.apilodgings.dto.place.PlaceDTO;
import com.digitalbooking.apilodgings.dto.product.CreateProductDTO;
import com.digitalbooking.apilodgings.dto.product.ProductDTO;
import com.digitalbooking.apilodgings.dto.product.ProductSmallDTO;
import com.digitalbooking.apilodgings.entity.Feature;
import com.digitalbooking.apilodgings.entity.Image;
import com.digitalbooking.apilodgings.entity.Place;
import com.digitalbooking.apilodgings.entity.Product;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.repository.IFeatureRepository;
import com.digitalbooking.apilodgings.repository.IImageRepository;
import com.digitalbooking.apilodgings.repository.IProductRepository;
import com.digitalbooking.apilodgings.response.ResponseError;
import com.digitalbooking.apilodgings.service.place.PlaceServiceImpl;
import com.digitalbooking.apilodgings.utility.MapperUtils;
import com.digitalbooking.apilodgings.utility.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final PlaceServiceImpl placeService;
    private final IFeatureRepository featureRepository;
    private final ObjectMapper mapper;


    @Autowired
    public ProductServiceImpl(IProductRepository productRepository,
                              PlaceServiceImpl placeService,
                              IFeatureRepository featureRepository,
                              IImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.placeService = placeService;
        this.featureRepository = featureRepository;
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }


    @Override
    public ProductDTO createProduct(CreateProductDTO createProductDTO) throws NotFoundException {

        CreatePlaceDTO placeToSave = new CreatePlaceDTO();
        placeToSave.setTitle(createProductDTO.getTitle());
        placeToSave.setDescription(createProductDTO.getDescription());
        placeToSave.setLatitude(0);
        placeToSave.setLongitude(0);
        placeToSave.setAddress(createProductDTO.getAddress());
        placeToSave.setCategory(createProductDTO.getCategory());
        placeToSave.setCity(createProductDTO.getCity());
        placeToSave.setPolicy(createProductDTO.getPolicy());
        PlaceDTO placeSaved = placeService.createPlace(placeToSave);
        Place place = mapper.convertValue(placeSaved, Place.class);

        Product productToSave = ProductMapper.fromCreateProductDTO(createProductDTO);
        productToSave.setPlace(place);

        Product productSaved = productRepository.save(productToSave);

        for (CreateImageDTO createImageDTO : createProductDTO.getImages())
        {
            Image image = new Image();
            image.setTitle(String.format("p-%s", productSaved.getId()));
            image.setUrl(createImageDTO.getUrl());
            productSaved.addImage(image);
        }

        productSaved = productRepository.save(productSaved);

        for (CreateFeatureDTO createFeatureDTO : createProductDTO.getFeatures()){
            try {
                Feature featureFound =
                        featureRepository.findByTitleIgnoreCase(createFeatureDTO.getTitle())
                                .orElseThrow(() -> new NotFoundException(String.format("Feature with name: %s", createFeatureDTO.getTitle())));
                productSaved.addFeature(featureFound);

            } catch (NotFoundException ignored) {
            }
        }

        productSaved = productRepository.save(productSaved);

        ProductDTO productDTO = mapper.convertValue(productSaved, ProductDTO.class);

        Collections.sort(productDTO.getImages());
        Collections.sort(productDTO.getFeatures());

        return productDTO;
    }

    @Override
    public ProductDTO findProductById(Integer productId) throws NotFoundException {
        Product product = productRepository.findBy_Id(productId).orElse(null);

        if (product == null || product.getId() == null) {
            ResponseError responseException = new ResponseError(String.format("Not found product with id: '%s'", productId));
            throw new NotFoundException(responseException);
        }

        ProductDTO productDTO = MapperUtils.mapEntity(product, ProductDTO.class, mapper);

        Collections.sort(productDTO.getImages());
        Collections.sort(productDTO.getFeatures());

        return productDTO;
    }

    @Override
    public ProductDTO findProductByTitle(String productTitle) throws NotFoundException {
        Product product = productRepository.findBy_Title(productTitle).orElse(null);

        if (product == null) {
            ResponseError responseException = new ResponseError(String.format("Not found product with title: '%s'", productTitle));
            throw new NotFoundException(responseException);
        }

        ProductDTO productDTO = mapper.convertValue(product, ProductDTO.class);

        Collections.sort(productDTO.getImages());
        Collections.sort(productDTO.getFeatures());

        return productDTO;
    }

    @Override
    public List<ProductSmallDTO> findAllProducts() {

        List<Product> productsFound = productRepository.findAllProducts();
        List<ProductSmallDTO> productsDTOFound = new ArrayList<>();

        for (Product product : productsFound) {
            productsDTOFound.add(mapper.convertValue(product, ProductSmallDTO.class));
        }

        return productsDTOFound;
    }

    @Override
    public boolean deleteById(Integer id) throws NotFoundException {

        if (productRepository.findBy_Id(id).isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format("Product with id: %s", id));
        }

        return true;
    }

    @Override
    public List<ProductSmallDTO> findAllProductsRandom(Integer quantity) {
        List<Product> productsFound = productRepository.findAllProducts();
        List<ProductSmallDTO> randomProductsDTO = new ArrayList<>();
        Random rand = new Random();

        int MAX_ITEMS = quantity == null || quantity == 0 || quantity > productsFound.size()
                ? Math.min(productsFound.size(), 10) : quantity;

        for (int i = 1; i <= MAX_ITEMS; i++) {
            int randomIndex = rand.nextInt(productsFound.size());
            Product product = productsFound.remove(randomIndex);
            randomProductsDTO.add(mapper.convertValue(product, ProductSmallDTO.class));
        }

        return randomProductsDTO;
    }

    @Override
    public List<ProductSmallDTO> findAllProductsByCategoryTitle(String categoryTitle) {
        List<Product> productsFound = productRepository.findAllByPlace_Category_TitleIgnoreCaseAndDeletedIsFalse(categoryTitle);
        List<ProductSmallDTO> productsDTOFound = new ArrayList<>();

        for (Product product : productsFound) {
            productsDTOFound.add(mapper.convertValue(product, ProductSmallDTO.class));
        }

        return productsDTOFound;
    }

    @Override
    public List<ProductSmallDTO> findAllProductsByCityTitle(String cityTitle) {
        List<Product> productsFound = productRepository.findAllByPlace_City_TitleIgnoreCaseAndDeletedIsFalse(cityTitle);
        List<ProductSmallDTO> productsDTOFound = new ArrayList<>();

        MapperUtils.mapListEntity(productsFound, productsDTOFound, ProductSmallDTO.class, mapper);

        return productsDTOFound;
    }

    @Override
    public List<ProductSmallDTO> findAllProductsByCityTitleAndReservationDate(String cityTitle, Date checkIn, Date checkOut) {

        List<Product> productsNotAvailable =
                productRepository.findAllBy_CityTitle_And_Reservation_CheckIn_Or_CheckOut_IsBetween(cityTitle, checkIn, checkOut, checkIn, checkOut);

        List<Product> productsFound =
                productRepository.findAllByPlace_City_TitleIgnoreCaseAndDeletedIsFalse(cityTitle);

        List<ProductSmallDTO> productsDTOFound = new ArrayList<>();

        if (productsNotAvailable.size() == 0) {
            MapperUtils.mapListEntity(productsFound, productsDTOFound, ProductSmallDTO.class, mapper);
            return productsDTOFound;
        }

        for (Product product : productsFound) {
            var equal = productsNotAvailable.removeIf(productNotAvailable -> productNotAvailable.getId().equals(product.getId()));
            if (!equal) {
                productsDTOFound.add(mapper.convertValue(product, ProductSmallDTO.class));
            }
        }

        return productsDTOFound;
    }

}
