package com.api.ecommerce.product.dto;

import com.api.ecommerce.product.model.Image;

import java.util.Date;
import java.util.List;

public record ProductResponse(
        String id,
        String createdBy,
        String category,
        List<String> subcategories,
        String title,
        List<Image> images,
        String description,
        double price,
        int stock,
        int discountPercentage,
        Date createdAt
) {}