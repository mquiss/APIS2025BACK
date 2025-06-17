package com.api.ecommerce.user.mapper;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.user.dto.UserResponse;
import com.api.ecommerce.user.model.User;

public interface UserMapper {
    User toUser(RegisterRequest registerRequest);
    UserResponse toUserResponse(User user);
}
