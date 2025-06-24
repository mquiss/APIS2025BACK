package com.api.ecommerce.category.mapper;

import com.api.ecommerce.category.dto.CategoryResponse;
import com.api.ecommerce.category.model.Category;
import com.api.ecommerce.category.model.Subcategory;
import org.springframework.stereotype.Component;

@Component

public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId().toString())
                .name(category.getName())
                .subcategories(category.getSubcategories().stream().map(Subcategory::getName).toList())
                .build();
    }
}
