package com.digitalbooking.apilodgings.service.product;

import com.digitalbooking.apilodgings.dto.product.ProductDTO;
import com.digitalbooking.apilodgings.dto.product.ProductMiniDTO;
import com.digitalbooking.apilodgings.entity.Product;
import com.digitalbooking.apilodgings.exception.NotFoundException;
import com.digitalbooking.apilodgings.repository.IPlaceRepository;
import com.digitalbooking.apilodgings.repository.IProductRepository;
import com.digitalbooking.apilodgings.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final IPlaceRepository placeRepository;
    private final ObjectMapper mapper;


    @Autowired
    public ProductServiceImpl(IProductRepository productRepository, IPlaceRepository placeRepository) {
        this.productRepository = productRepository;
        this.placeRepository = placeRepository;
        this.mapper = new ObjectMapper();
    }


    /* TODO:
        Logic for Create and Update product
    Traer el NewProductDTO y usar los servicios de Feature y Image,
     para almacenar las images  y caracteristicas del producto
     con la asociación correspondiente.
     */
    @Override
    public ProductDTO createProduct(ProductDTO productRequestDTO) {

        /* TODO:
            Implement functionality
        Product productToSave = mapper.convertValue(productRequestDTO, Product.class);
        Product productSaved = productRepository.save(productToSave);
        */

        return null;
    }

    @Override
    public ProductDTO findProductById(Integer productId) throws NotFoundException {
        Product product = productRepository.findByIdAndDeletedIsFalse(productId).orElse(null);

        if (product == null || product.getId() == null) {
            Response responseException = new Response(String.format("Not found product with id: '%s'", productId));
            throw new NotFoundException(responseException);
        }

        ProductDTO productDTO = mapper.convertValue(product, ProductDTO.class);

        Collections.sort(productDTO.getImages());
        Collections.sort(productDTO.getFeatures());

        return productDTO;
    }

    @Override
    public ProductDTO findProductByTitle(String productTitle) throws NotFoundException {
        Product product = productRepository.findByTitleIgnoreCaseAndDeletedIsFalse(productTitle).orElse(null);

        if (product == null) {
            Response responseException = new Response(String.format("Not found product with title: '%s'", productTitle));
            throw new NotFoundException(responseException);
        }

        ProductDTO productDTO = mapper.convertValue(product, ProductDTO.class);

        Collections.sort(productDTO.getImages());
        Collections.sort(productDTO.getFeatures());

        return productDTO;
    }

    @Override
    public List<ProductMiniDTO> findAllProducts() {

        List<Product> productsFound = productRepository.findAllByDeletedIsFalse();
        List<ProductMiniDTO> productsDTOFound = new ArrayList<>();

        for (Product product : productsFound) {
            productsDTOFound.add(mapper.convertValue(product, ProductMiniDTO.class));
        }

        return productsDTOFound;
    }

    @Override
    public List<ProductMiniDTO> findAllProductsRandom(Integer quantity) {
        List<Product> productsFound = productRepository.findAllByDeletedIsFalse();
        List<ProductMiniDTO> randomProductsDTO = new ArrayList<>();
        Random rand = new Random();

        int MAX_ITEMS = quantity == null || quantity == 0 || quantity > productsFound.size()
                ? Math.min(productsFound.size(), 10) : quantity;

        for (int i = 1; i <= MAX_ITEMS; i++) {
            int randomIndex = rand.nextInt(productsFound.size());
            Product product = productsFound.remove(randomIndex);
            randomProductsDTO.add(mapper.convertValue(product, ProductMiniDTO.class));
        }

        return randomProductsDTO;
    }

    @Override
    public List<ProductMiniDTO> findAllProductsByCategoryTitle(String categoryTitle) {
        List<Product> productsFound = productRepository.findAllByPlace_Category_TitleIgnoreCaseAndDeletedIsFalse(categoryTitle);
        List<ProductMiniDTO> productsDTOFound = new ArrayList<>();

        for (Product product : productsFound) {
            productsDTOFound.add(mapper.convertValue(product, ProductMiniDTO.class));
        }

        return productsDTOFound;
    }

    @Override
    public List<ProductMiniDTO> findAllProductsByCityTitle(String cityTitle) {
        List<Product> productsFound = productRepository.findAllByPlace_City_TitleIgnoreCaseAndDeletedIsFalse(cityTitle);
        List<ProductMiniDTO> productsDTOFound = new ArrayList<>();

        for (Product product : productsFound) {
            productsDTOFound.add(mapper.convertValue(product, ProductMiniDTO.class));
        }

        return productsDTOFound;
    }
}
