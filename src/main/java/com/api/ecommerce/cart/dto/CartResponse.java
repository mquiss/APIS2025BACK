package com.api.ecommerce.cart.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponse {
    private String userId;
    private List<CartItemResponse> products;
}