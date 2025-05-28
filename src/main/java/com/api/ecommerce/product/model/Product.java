package com.api.ecommerce.product.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String userId;
    private String categoryId;

    private List<String> subcategoryIds;
    private String title;
    private List<Image> images;
    private String description;
    private double price;
    private int stock;
    private int discountPercentage;
    private boolean isFeatured;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}