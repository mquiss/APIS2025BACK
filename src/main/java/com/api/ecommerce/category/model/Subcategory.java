package com.api.ecommerce.category.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Subcategory {
    @Field("id")
    private String id;
    private String name;
}
