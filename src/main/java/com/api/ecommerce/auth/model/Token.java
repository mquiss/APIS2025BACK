package com.api.ecommerce.auth.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "tokens")
public class Token {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String token;
    private ObjectId userId;
    private Type type;
    private boolean revoked;
    private boolean expired;
}