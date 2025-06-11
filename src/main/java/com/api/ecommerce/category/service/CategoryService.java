package com.api.ecommerce.category.service;

import com.api.ecommerce.category.model.Category;
import com.api.ecommerce.category.repository.CategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findCategoryById(ObjectId id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
