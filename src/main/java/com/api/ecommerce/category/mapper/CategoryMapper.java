package com.api.ecommerce.category.mapper;

import com.api.ecommerce.category.dto.CategoryResponse;
import com.api.ecommerce.category.model.Category;

public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
}
