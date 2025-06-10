package com.model;

import java.util.List;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carts")
@Data
public class CartData {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private List<Product> products;
}
