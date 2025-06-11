package com.api.ecommerce.product.service;

import com.api.ecommerce.product.dto.ProductDTO;
import com.api.ecommerce.product.mapper.ProductMapper;
import com.api.ecommerce.product.model.Product;
import com.api.ecommerce.product.repository.ProductRepository;
import org.bson.internal.BsonUtil;
import org.bson.types.ObjectId;
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
        ObjectId objectId = new ObjectId(id);
        return productRepository.findById(objectId)
                .map(ProductMapper.INSTANCE::toProductDTO)
                .orElse(null);

    }

    public List<ProductDTO> getProductsByCategory(String categoryId) {
        ObjectId catId = new ObjectId(categoryId);
        List<Product> products = productRepository.findByCategoryId(catId);
        return products.stream()
                .map(ProductMapper.INSTANCE::toProductDTO)
                .collect(Collectors.toList());
    }
}