package com.api.ecommerce.product.model;

import com.api.ecommerce.product.dto.ProductDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data //getters y setters con lombok
@Document(collection = "product")
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
    private double discountPercentage;
    private boolean isFeatured;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}