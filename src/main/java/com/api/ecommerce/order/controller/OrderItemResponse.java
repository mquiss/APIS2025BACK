package com.api.ecommerce.order.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {
    private String productId;
    private int quantity;
    private double unitPrice;
}
