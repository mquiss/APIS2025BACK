package com.api.ecommerce.cart.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductsRequest {
    private List<CartItemRequest> products;
}