package com.api.ecommerce.user.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String email;
    private String username; // FIXME: por alguna razon no acepta usernames duplicados en db
    private String password;
    private String firstName;
    private String lastName;
    private Address address;
    private String avatar;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
