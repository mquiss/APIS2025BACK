package com.api.ecommerce.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CartItemRequest {
    @NotBlank(message = "Product id is required")
    private String productId;
    @Min(value = 1, message = "Quantity cannot be less than 1")
    private int quantity; // TODO: cambiar todo de int a Integer para validar null
}
