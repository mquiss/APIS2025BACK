package com.api.ecommerce.category.mapper;

import com.api.ecommerce.category.dto.CategoryResponse;
import com.api.ecommerce.category.dto.SubcategoryResponse;
import com.api.ecommerce.category.model.Category;
import com.api.ecommerce.category.model.Subcategory;

public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
    SubcategoryResponse toSubcategoryResponse(Subcategory subcategory);
}
