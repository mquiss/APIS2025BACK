package com.api.ecommerce.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CartItemRequest {
    @NotBlank(message = "productId is required")
    private String productId;
    @Min(value = 1, message = "quantity cannot be less than 1")
    private int quantity;
}
