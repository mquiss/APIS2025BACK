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
    @Min(value = 0, message = "Price must be non-negative")
    private int price; // TODO: cambiar todo de int a Integer para validar null
    @Min(value = 0, message = "Stock must be non-negative")
    private int stock; // TODO: cambiar todo de int a Integer para validar null
    @Min(value = 0, message = "Discount percentage must be non-negative")
    @Max(value = 100, message = "Discount percentage must be 100 or less")
    private int discountPercentage; // TODO: cambiar todo de int a Integer para validar null
}