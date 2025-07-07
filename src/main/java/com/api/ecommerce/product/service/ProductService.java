package com.api.ecommerce.product.service;

import com.api.ecommerce.category.service.CategoryService;
import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.common.exception.RecursoNoEncontradoException;
import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.dto.ProductResponse;
import com.api.ecommerce.product.dto.StockRequest;
import com.api.ecommerce.product.mapper.ProductMapper;
import com.api.ecommerce.product.model.Product;
import com.api.ecommerce.product.repository.ProductRepository;
import com.api.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final Mapper mapper;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public List<ProductResponse> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public PageResponse<ProductResponse> getAllProductsPage(Pageable pageable) {
        Page<Product> page = productRepository
                .findAll(pageable);

        return productMapper
                .toPageResponse(page);
    }

    public ProductResponse getProductById(String id) {
        return productRepository
                .findById(mapper.mapStringToObjectId(id))
                .map(productMapper::toProductResponse)
                .orElseThrow(RecursoNoEncontradoException::new);
    }

    public List<ProductResponse> getProductsByCategory(String categoryId) {
        ObjectId objectCategoryId = mapper.mapStringToObjectId(categoryId);
        categoryService.findCategoryById(objectCategoryId);

        return productRepository
                .findByCategoryId(objectCategoryId)
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        userService.findUserById(mapper.mapStringToObjectId(productRequest.getUserId()));
        categoryService.findCategoryById(mapper.mapStringToObjectId(productRequest.getCategoryId()));

        for (String subcategoryId : productRequest.getSubcategoryIds()) {
            categoryService.findSubcategoryById(productRequest.getCategoryId(), subcategoryId);
        }

        Product product = productMapper
                .toProduct(productRequest);

        productRepository.save(product);

        return productMapper
                .toProductResponse(product);
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest) { // TODO: crear otro dto para definir los datos que se pueden actualizar (excluir ids cat, subcat, user)
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
                .toProductResponse(newProduct);
    }

    public void deleteProduct(String id) {
        Product product = productRepository
                .findById(mapper.mapStringToObjectId(id))
                .orElseThrow(RecursoNoEncontradoException::new);

        productRepository.delete(product);
    }

    public List<ProductResponse> getProductsByUserId(String userId) {
        ObjectId objectUserId = mapper.mapStringToObjectId(userId);

        userService.findUserById(objectUserId);

        return productRepository
                .findByUserId(objectUserId)
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse updateStock(String id, StockRequest stock) {
        Product product = productRepository.findById(mapper.mapStringToObjectId(id)).orElseThrow(RecursoNoEncontradoException::new);
        product.setStock(stock.stock());

        productRepository.save(product);

        return productMapper.toProductResponse(product);
    }

    public Product getProductById(ObjectId id) {
        return productRepository.findById(id).orElseThrow(RecursoNoEncontradoException::new);
    }
}