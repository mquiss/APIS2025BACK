package com.api.ecommerce.user.mapper;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.user.dto.UserResponse;
import com.api.ecommerce.user.model.Address;
import com.api.ecommerce.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final PasswordEncoder passwordEncoder;

    @Override
    public User toUser(RegisterRequest registerRequest) {
        return User.builder()
                .username(registerRequest.getFirstName().charAt(0)+registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .address(Address.builder().build())
                .avatar(null)
                .build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId().toString())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .build();
    }
}
