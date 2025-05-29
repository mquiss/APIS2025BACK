package com.api.ecommerce.product.dto;

import com.api.ecommerce.product.model.Image;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
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
}