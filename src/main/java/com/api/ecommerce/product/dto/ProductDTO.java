package com.api.ecommerce.product.dto;

import com.api.ecommerce.product.model.Image;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDTO {
    private String id;
    private String userId;
    private String categoryId;
    private List<String> subcategoryIds;
    private String title;
    private List<Image> images;
    private String description;
    private double price;
    private int stock;
    private double discountPercentage;
    private boolean isFeatured;
    private LocalDateTime createdAt;
}