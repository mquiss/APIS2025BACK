package com.api.ecommerce.product.repository;

import com.api.ecommerce.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ 'categories.categoryId': ?0 }")
    List<Product> findByCategoriesCategoryId(String categoryId);
}