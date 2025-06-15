package com.api.ecommerce.user.dto;

import com.api.ecommerce.user.model.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private int passwordLength;
    private String firstName;
    private String lastName;
    private Address address;
    private String avatar;
}