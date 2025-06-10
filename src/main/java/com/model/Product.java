package com.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Product {
    @Field("_id")
    private ObjectId id;

    @Field("title")
    private String name;

    @Field("price")
    private Double price;

    private int quantity;

}
