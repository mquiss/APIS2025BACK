package com.api.ecommerce.auth.service;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.auth.dto.TokenResponse;
import com.api.ecommerce.auth.jwt.JwtService;
import com.api.ecommerce.user.dto.UserResponse;
import com.api.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import com.api.ecommerce.auth.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public TokenResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                ));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        return new TokenResponse(jwtService.generateToken(userDetails));
    }

    public UserResponse register(RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
        // TODO crear carrito de compras vacío o con productos de register request
    }
}

