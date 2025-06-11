package com.api.ecommerce.product.dto;

import com.api.ecommerce.product.model.Image;
import java.util.List;

public record ProductRequest(
        String userId,
        String categoryId,
        List<String> subcategoryIds,
        String title,
        List<Image> images,
        String description,
        double price,
        int discountPercentage,
        int stock
) {}
