package com.api.ecommerce.cart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder
public class CartRequest {
    @NotBlank(message = "User id is required")
    private String userId;
    private List<CartItemRequest> products;
}