package com.api.ecommerce.category.service;

import com.api.ecommerce.category.dto.CategoryResponse;
import com.api.ecommerce.category.mapper.CategoryMapper;
import com.api.ecommerce.category.model.Category;
import com.api.ecommerce.category.repository.CategoryRepository;
import com.api.ecommerce.common.exception.RecursoNoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    public Category findCategoryById(ObjectId id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(RecursoNoEncontradoException::new);
    }
}
