package com.api.ecommerce.user.repository;

import com.api.ecommerce.user.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByEmail(String email);
    List<User> findByUsernameAndPassword(String username, String password);
}
