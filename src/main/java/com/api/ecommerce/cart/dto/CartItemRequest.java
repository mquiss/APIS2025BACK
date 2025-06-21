package com.api.ecommerce.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
public class CartItemRequest {
    @NotBlank(message = "Product id is required")
    private String productId;
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity cannot be less than 1")
    private Integer quantity;
}
