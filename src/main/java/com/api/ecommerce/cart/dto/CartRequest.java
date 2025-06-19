package com.api.ecommerce.cart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class CartRequest {
    @NotBlank(message = "User id is required")
    private String userId;
    private List<CartItemRequest> products; //puede estar vacio, deberia guardar products = []

}