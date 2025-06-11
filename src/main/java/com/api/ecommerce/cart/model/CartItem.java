package com.api.ecommerce.cart.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class CartItem {
    private ObjectId productId;
    private int quantity;
}
