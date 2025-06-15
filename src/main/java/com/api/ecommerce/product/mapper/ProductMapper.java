package com.api.ecommerce.product.mapper;

import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.dto.ProductResponse;
import com.api.ecommerce.product.model.Product;
import org.springframework.data.domain.Page;

public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
    Product toProduct(ProductRequest productRequest);
    PageResponse<ProductResponse> toPageResponse(Page<Product> page);
}