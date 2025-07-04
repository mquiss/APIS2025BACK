package com.api.ecommerce.cart.repository;

import com.api.ecommerce.cart.model.Cart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, ObjectId> {
    Optional<Cart> findByUserId(ObjectId userId);
}
