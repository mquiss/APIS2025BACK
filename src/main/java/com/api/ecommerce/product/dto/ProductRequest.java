package com.api.ecommerce.product.dto;

import com.api.ecommerce.product.model.Image;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductRequest {
    @NotBlank(message = "User id is required")
    private String userId;
    @NotBlank(message = "Category id is required")
    private String categoryId;
    @NotEmpty(message = "Subcategory ids cannot be empty")
    private List<String> subcategoryIds;
    @NotBlank(message = "Title is required")
    private String title;
    @NotEmpty(message = "At least one image is required")
    private List<Image> images;
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be non-negative")
    private Double price;
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be non-negative")
    private Integer stock;
    @NotNull(message = "Discount percentage is required")
    @Min(value = 0, message = "Discount percentage must be non-negative")
    @Max(value = 100, message = "Discount percentage must be 100 or less")
    private Integer discountPercentage;
}