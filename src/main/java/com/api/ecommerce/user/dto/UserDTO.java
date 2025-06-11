package com.api.ecommerce.user.dto;

import com.api.ecommerce.user.model.Address;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.Instant;

@Data
public class UserDTO {
    private ObjectId id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;  
    private Instant createdAt;
    private Instant updatedAt;
    private Address address;
}
