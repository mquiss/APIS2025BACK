package com.api.ecommerce.category.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "product_categories")
public class ProductCategory {
    @Id
    private String id;
    private String productId;
    private String categoryId;
    private List<String> subcategoryIds;
}
