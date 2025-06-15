package com.api.ecommerce.product.mapper;

import com.api.ecommerce.category.model.Subcategory;
import com.api.ecommerce.category.service.CategoryService;
import com.api.ecommerce.common.dto.PageResponse;
import com.api.ecommerce.common.util.Mapper;
import com.api.ecommerce.product.dto.ProductRequest;
import com.api.ecommerce.product.dto.ProductResponse;
import com.api.ecommerce.product.model.Product;
import com.api.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper{
    private final Mapper mapper;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public ProductResponse toProductResponse(Product product) {
        String username = userService.findUserById(product.getUserId()).getUsername();
        String categoryName = categoryService.findCategoryById(product.getCategoryId()).getName();
        List<String> subcategoryNames = categoryService.findCategoryById(product.getCategoryId())
                .getSubcategories()
                .stream()
                .map(Subcategory::getName)
                .collect(Collectors.toList());

        return ProductResponse.builder()
                .id(product.getId().toString())
                .username(username)
                .category(categoryName)
                .subcategories(subcategoryNames)
                .title(product.getTitle())
                .images(product.getImages())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .discountPercentage(product.getDiscountPercentage())
                .isFeatured(product.isFeatured())
                .createdAt(LocalDateTime.ofInstant(product.getCreatedAt(), ZoneId.systemDefault()))
                .build();
    }

    @Override
    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .userId(mapper.mapStringToObjectId(productRequest.getUserId()))
                .categoryId(mapper.mapStringToObjectId(productRequest.getCategoryId()))
                .subcategoryIds(productRequest.getSubcategoryIds())
                .title(productRequest.getTitle())
                .images(productRequest.getImages())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .discountPercentage(productRequest.getDiscountPercentage())
                .build();
    }

    @Override
    public PageResponse<ProductResponse> toPageResponse(Page<Product> page) {
        List<ProductResponse> content = page.getContent()
                .stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());

        return new PageResponse<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst()
        );
    }
}
