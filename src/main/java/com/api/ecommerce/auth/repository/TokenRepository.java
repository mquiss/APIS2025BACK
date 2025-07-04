package com.api.ecommerce.auth.repository;

import com.api.ecommerce.auth.model.Token;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token, ObjectId> {
    List<Token> findAllByUserIdAndExpiredFalseAndRevokedFalse(ObjectId userId);
    Optional<Token> findByToken(String token);
}