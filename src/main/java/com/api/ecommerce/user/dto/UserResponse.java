package com.api.ecommerce.user.dto;

import com.api.ecommerce.user.model.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String id;
    private String alias;
    private String email;
    private String firstName;
    private String lastName;
    private Address address;
    private String avatar;
}