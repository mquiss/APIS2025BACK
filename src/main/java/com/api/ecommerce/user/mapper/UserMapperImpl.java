package com.api.ecommerce.user.mapper;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.user.dto.UserResponse;
import com.api.ecommerce.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toUser(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .email(user.getEmail())
                .passwordLength(user.getPassword().length())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .build();
    }
}
