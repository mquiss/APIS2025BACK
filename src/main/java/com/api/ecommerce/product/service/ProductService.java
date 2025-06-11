package com.api.ecommerce.product.service;

import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.product.dto.ProductDTO;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.mapper.ProductMapper;
import com.api.ecommerce.product.model.Product;
import com.api.ecommerce.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PageResponse<ProductDTO> getAllProducts(Pageable pageable) {
        Page<ProductDTO> page = productRepository.findAll(pageable)
                .map(ProductMapper.INSTANCE::toProductDTO);

        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst()
        );
    }

    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id).orElse(null);
        return product == null ? null : ProductMapper.INSTANCE.toProductDTO(product);
    }

    public void addProduct(ProductRequest productRequest) {
        Product product = ProductMapper.INSTANCE.toProduct(productRequest);
        productRepository.save(product);
    }
}