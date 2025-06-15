package com.api.ecommerce.cart.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "carts")
public class Cart {
    @Id
    private ObjectId id;

    private ObjectId userId;
    private List<CartItem> products;
}
