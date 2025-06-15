package com.api.ecommerce.cart.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponse {
    private String productId;
    private String title;
    private String image;
    private int discountPercentage;
    private double price;
    private int quantity;
    private int stock;
}