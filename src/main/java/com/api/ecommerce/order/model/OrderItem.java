package com.api.ecommerce.order.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class OrderItem {
    private ObjectId productId;
    private int quantity;
    private double unitPrice;
}
