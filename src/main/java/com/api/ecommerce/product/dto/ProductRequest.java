package com.api.ecommerce.product.dto;

import java.util.List;

public class ProductRequest {
    private String userId;
    private String categoryId;
    private List<String> subcategoryIds;
    private String title;
    private List<String> images;
    private String description;
    private int price;
    private int stock;
    private int discountPercentage;
}
