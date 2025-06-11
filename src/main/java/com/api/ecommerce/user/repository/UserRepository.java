package com.api.ecommerce.user.repository;

import com.api.ecommerce.user.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByEmail(String email);
}
