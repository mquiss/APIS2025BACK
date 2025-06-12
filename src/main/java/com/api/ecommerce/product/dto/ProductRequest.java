package com.api.ecommerce.product.dto;

import com.api.ecommerce.product.model.Image;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductRequest {
    @NotBlank(message = "userId is required")
    private String userId;
    @NotBlank(message = "categoryId is required")
    private String categoryId;
    @NotEmpty(message = "subcategoryIds cannot be empty")
    private List<String> subcategoryIds;
    @NotBlank(message = "title is required")
    private String title;
    @NotEmpty(message = "At least one image is required")
    private List<Image> images;
    @NotBlank(message = "description is required")
    private String description;
    @Min(value = 0, message = "price must be non-negative")
    private int price;
    @Min(value = 0, message = "stock must be non-negative")
    private int stock;
    @Min(value = 0)
    @Max(value = 100)
    private int discountPercentage;
}