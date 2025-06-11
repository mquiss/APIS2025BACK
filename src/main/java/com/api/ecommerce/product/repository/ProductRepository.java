package com.api.ecommerce.product.repository;

import com.api.ecommerce.product.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    List<Product> findByCategoryId(ObjectId categoryId);
    List<Product> findByUserId(ObjectId userId);
}