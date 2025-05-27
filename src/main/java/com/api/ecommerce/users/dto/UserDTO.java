package com.api.ecommerce.users.dto;

import com.api.ecommerce.users.model.Address;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private String id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;  
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Address address;
}
