package com.api.ecommerce.product.dto;

import com.api.ecommerce.product.model.Image;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProductResponse {
    private String id;
    private String userId;
    private String username;
    private String category;
    private List<String> subcategories;
    private String title;
    private List<Image> images;
    private String description;
    private Double price;
    private Integer stock;
    private Integer discountPercentage;
    private boolean isFeatured;
    private LocalDateTime createdAt;
}
