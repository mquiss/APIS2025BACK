package com.api.ecommerce.product.service;

import com.api.ecommerce.product.dto.ProductDTO;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.mapper.ProductMapper;
import com.api.ecommerce.product.model.Product;
import com.api.ecommerce.product.repository.ProductRepository;
import org.bson.internal.BsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(ProductMapper.INSTANCE::toProductDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElse(null);
        return product == null ? null : ProductMapper.INSTANCE.toProductDTO(product);
    }

    public ProductDTO createProduct(ProductRequest productRequest) {
        Product product = ProductMapper.INSTANCE.toProduct(productRequest);
        productRepository.save(product);
        return ProductMapper.INSTANCE.toProductDTO(product);
    }

    public ProductDTO updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElse(null);
        // logica update y obtener nuevo producto era save?
        return product == null ? null : ProductMapper.INSTANCE.toProductDTO(product);
    }

    public ProductDTO deleteProduct(String id) {
        Product product = productRepository.findById(id).orElse(null);
        productRepository.delete(product);
        return ProductMapper.INSTANCE.toProductDTO(product);
    }

    public List<ProductDTO> getProductsByUserId(String userId) {
        return productRepository.findByUserId(userId).stream().map(ProductMapper.INSTANCE::toProductDTO).collect(Collectors.toList());
    }
}