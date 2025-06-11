package com.api.ecommerce.category.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "categories")
public class Category {
    @Id
    private ObjectId id;
    private String name;
    private List<Subcategory> subcategories;
}
