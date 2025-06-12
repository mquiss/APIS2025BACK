package com.api.ecommerce.product.mapper;

import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.product.dto.ProductDTO;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.dto.ProductResponse;
import com.api.ecommerce.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse toProductResponse(Product product);

    Product toProduct(ProductRequest productRequest);

    ProductDTO toProductDTO(Product product);

    PageResponse<ProductResponse> toProductPageResponse(Page<ProductDTO> page);
}