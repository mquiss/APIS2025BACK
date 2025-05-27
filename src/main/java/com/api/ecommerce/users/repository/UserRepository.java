package com.api.ecommerce.users.repository;

import com.api.ecommerce.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);
    
    // Puedes agregar más métodos personalizados si es necesario
}