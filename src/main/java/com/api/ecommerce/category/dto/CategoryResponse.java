package com.api.ecommerce.category.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryResponse {
    private String id;
    private String name;
    private List<String> subcategories;
}