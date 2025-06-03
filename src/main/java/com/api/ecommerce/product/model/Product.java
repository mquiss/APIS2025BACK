package com.api.ecommerce.product.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String userId;
    
    @Data
    public static class CategoryReference {
        private String categoryId;
        private List<String> subcategoryIds;
    }
    
    private List<CategoryReference> categories;
    
    private String title;
    private List<Image> images;
    private String description;
    private double price;
    private int stock;
    private int discountPercentage;
    
    @Field("isFeatured")
    private boolean featured;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
}