package com.api.ecommerce.product.service;

import com.api.ecommerce.category.service.CategoryService;
import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.common.exception.RecursoNoEncontradoException;
import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.dto.ProductResponse;
import com.api.ecommerce.product.mapper.ProductMapper;
import com.api.ecommerce.product.mapper.ProductMapperImpl;
import com.api.ecommerce.product.model.Product;
import com.api.ecommerce.product.repository.ProductRepository;
import com.api.ecommerce.user.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final Mapper mapper;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.mapper = new Mapper();
        this.productMapper = new ProductMapperImpl(mapper);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(product -> productMapper.toProductResponse(product, userService, categoryService))
                .collect(Collectors.toList());
    }

    public PageResponse<ProductResponse> getAllProductsPage(Pageable pageable) {
        Page<Product> page = productRepository
                .findAll(pageable);

        return productMapper
                .toPageResponse(page, userService, categoryService);
    }

    public ProductResponse getProductById(String id) {
        return productRepository
                .findById(mapper.mapStringToObjectId(id))
                .map(product -> productMapper.toProductResponse(product, userService, categoryService))
                .orElseThrow(RecursoNoEncontradoException::new);
    }

    public List<ProductResponse> getProductsByCategory(String categoryId) {
        return productRepository
                .findByCategoryId(mapper.mapStringToObjectId(categoryId))
                .stream()
                .map(product -> productMapper.toProductResponse(product, userService, categoryService))
                .collect(Collectors.toList());
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper
                .toProduct(productRequest);

        productRepository.save(product);

        return productMapper
                .toProductResponse(product, userService, categoryService);
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository
                .findById(mapper.mapStringToObjectId(id))
                .orElseThrow(RecursoNoEncontradoException::new);

        Product newProduct = productMapper
                .toProduct(productRequest);

        newProduct.setId(product.getId());
        newProduct.setFeatured(product.isFeatured());
        newProduct.setCreatedAt(product.getCreatedAt());

        productRepository.save(newProduct);

        return productMapper
                .toProductResponse(newProduct, userService, categoryService);
    }

    public boolean deleteProduct(String id) {
        try {
            Product product = productRepository
                    .findById(mapper.mapStringToObjectId(id))
                    .orElseThrow(RecursoNoEncontradoException::new);

            productRepository.delete(product);

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<ProductResponse> getProductsByUserId(String userId) {
        return productRepository
                .findByUserId(mapper.mapStringToObjectId(userId))
                .stream()
                .map(product -> productMapper.toProductResponse(product, userService, categoryService))
                .collect(Collectors.toList());
    }

    public Product getProductById(ObjectId id) {
        return productRepository.findById(id).orElseThrow(RecursoNoEncontradoException::new);
    }
}