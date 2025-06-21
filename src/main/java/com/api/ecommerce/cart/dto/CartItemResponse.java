package com.api.ecommerce.cart.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResponse {
    private String productId;
    private String title;
    private String image;
    private Integer discountPercentage;
    private Double price;
    private Integer quantity;
    private Integer stock;
}