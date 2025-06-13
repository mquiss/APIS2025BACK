package com.api.ecommerce.order.controller;

import com.api.ecommerce.order.model.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private String userId;
    private List<OrderItemResponse> products;
    private double subtotal;
    private LocalDateTime createdAt;
}
