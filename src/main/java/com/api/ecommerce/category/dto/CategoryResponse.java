package com.api.ecommerce.category.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import org.bson.types.ObjectId;



@Data
@Builder
public class CategoryResponse {
   
    private String id;
    private String name;
    private List<String> subcategories;
}
