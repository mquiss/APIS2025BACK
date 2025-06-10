package com.api.ecommerce.product.mapper;

import com.api.ecommerce.product.dto.ProductDTO;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toProductDTO(Product product); // product de db a product dto que se usa para response

    Product toProduct(ProductRequest productRequest); // product request (datos para crear un nuevo product) a product db
}