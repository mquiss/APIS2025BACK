package com.api.ecommerce.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {
    private String title;
    private String image;
    private int quantity;
    private double unitPrice;
}