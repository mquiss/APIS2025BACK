package com.api.ecommerce.category.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubcategoryResponse {
    private String id;
    private String name;
}
