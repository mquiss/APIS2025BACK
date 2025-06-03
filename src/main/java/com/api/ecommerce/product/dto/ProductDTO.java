package com.api.ecommerce.product.dto;

import com.api.ecommerce.product.model.Image;
import com.api.ecommerce.product.model.Product.CategoryReference;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private String userId;
    private List<CategoryReference> categories;
    private String title;
    private List<Image> images;
    private String description;
    private double price;
    private int stock;
    private int discountPercentage;
    private boolean featured;
}