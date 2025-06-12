package com.api.ecommerce.product.dto;

import com.api.ecommerce.product.model.Image;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductRequest {
    private String userId;
    private String categoryId;
    private List<String> subcategoryIds;
    private String title;
    private List<Image> images;
    private String description;
    private int price;
    private int stock;
    private int discountPercentage;
}