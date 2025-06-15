package com.api.ecommerce.cart.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@Builder
public class CartItem {
    private ObjectId productId;
    private int quantity;
}
