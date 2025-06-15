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
    private String username;
    private String category;
    private List<String> subcategories;
    private String title;
    private List<Image> images;
    private String description;
    private double price;
    private int stock;
    private int discountPercentage;
    private boolean isFeatured;
    private LocalDateTime createdAt;
}
