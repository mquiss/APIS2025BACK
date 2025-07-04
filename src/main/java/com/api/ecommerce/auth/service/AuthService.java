package com.api.ecommerce.auth.service;

import com.api.ecommerce.auth.dto.RegisterRequest;
import com.api.ecommerce.auth.dto.TokenResponse;
import com.api.ecommerce.auth.jwt.JwtService;
import com.api.ecommerce.auth.model.Token;
import com.api.ecommerce.auth.model.Type;
import com.api.ecommerce.auth.repository.TokenRepository;
import com.api.ecommerce.user.dto.UserResponse;
import com.api.ecommerce.user.model.User;
import com.api.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import com.api.ecommerce.auth.dto.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public TokenResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                ));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        User user = userService.findUserByEmail(loginRequest.getEmail());
        String accessToken = jwtService.generateToken(userDetails);

        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return new TokenResponse(accessToken);
    }

    public UserResponse register(RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
        // TODO crear carrito de compras vacío o con productos de register request
    }

    public void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .userId(user.getId())
                .token(jwtToken)
                .type(Type.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }

    public void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllByUserIdAndExpiredFalseAndRevokedFalse(user.getId());

        if(!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        }
    }
}

