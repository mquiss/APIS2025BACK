package com.api.ecommerce.product.repository;

import com.api.ecommerce.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}