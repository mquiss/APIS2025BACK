package com.api.ecommerce.category.service;

import com.api.ecommerce.category.dto.CategoryResponse;
import com.api.ecommerce.category.mapper.CategoryMapper;
import com.api.ecommerce.category.model.Category;
import com.api.ecommerce.category.model.Subcategory;
import com.api.ecommerce.category.repository.CategoryRepository;
import com.api.ecommerce.common.exception.RecursoNoEncontradoException;
import com.api.ecommerce.common.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final Mapper mapper;

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

    public Subcategory findSubcategoryById(String id, String subId) {
        Category category = categoryRepository.findById(mapper.mapStringToObjectId(id)).orElseThrow(RecursoNoEncontradoException::new);
        return category.getSubcategories().stream().filter(sub -> Objects.equals(sub.getId(), subId)).findFirst().orElseThrow(RecursoNoEncontradoException::new);
    }
}
