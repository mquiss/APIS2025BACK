package com.api.ecommerce.user.mapper;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.user.dto.UserResponse;
import com.api.ecommerce.user.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserMapper {
    User toUser(RegisterRequest registerRequest, PasswordEncoder passwordEncoder);
    UserResponse toUserResponse(User user);
}
