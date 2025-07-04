package com.api.ecommerce.product.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@Document(collection = "products")
public class Product {
    @Id
    private ObjectId id;

    private ObjectId userId;
    private ObjectId categoryId;
    private List<String> subcategoryIds;
    private String title;
    private List<Image> images;
    private String description;
    private Double price;
    private Integer stock;
    private Integer discountPercentage;
    private boolean isFeatured;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}

