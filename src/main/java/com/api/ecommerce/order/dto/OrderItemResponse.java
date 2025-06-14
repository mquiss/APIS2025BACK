package com.api.ecommerce.order.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {
    private String productId; // TODO: cambiar por datos que se muestran en front
    private int quantity;
    private double unitPrice;
}
