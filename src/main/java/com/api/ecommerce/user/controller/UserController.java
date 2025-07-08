package com.api.ecommerce.user.controller;

import com.api.ecommerce.user.dto.*;
import com.api.ecommerce.user.model.User;
import com.api.ecommerce.user.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getCurrentUser(userDetails));
    }

    @PatchMapping("/{id}/username")
    public ResponseEntity<UserResponse> updateUsername(@Valid @RequestBody UsernameRequest usernameRequest, @Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id) {
        return ResponseEntity.ok(userService.updateUsername(usernameRequest, id));
    }

    @PatchMapping("/{id}/firstName")
    public ResponseEntity<UserResponse> updateFirstName(@Valid @RequestBody FirstNameRequest firstNameRequest, @Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id) {
        return ResponseEntity.ok(userService.updateFirstName(firstNameRequest, id));
    }

    @PatchMapping("/{id}/lastName")
    public ResponseEntity<UserResponse> updateLastName(@Valid @RequestBody LastNameRequest lastNameRequest, @Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id) {
        return ResponseEntity.ok(userService.updateLastName(lastNameRequest, id));
    }

    @PatchMapping("/{id}/address")
    public ResponseEntity<UserResponse> updateAddress(@Valid @RequestBody AddressRequest addressRequest, @Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id) {
        return ResponseEntity.ok(userService.updateAddress(addressRequest, id));
    }

    @PatchMapping("/{id}/avatar")
    public ResponseEntity<UserResponse> updateAvatar(@Valid @RequestBody AvatarRequest avatarRequest, @Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id) {
        return ResponseEntity.ok(userService.updateAvatar(avatarRequest, id));
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<UserResponse> updateEmail(@Valid @RequestBody EmailRequest emailRequest, @Size(min = 24, max = 24, message = "id must be 24 characters long") @PathVariable String id) {
        return ResponseEntity.ok(userService.updateEmail(emailRequest, id));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<UserResponse> updatePassword(
            @Valid @RequestBody PasswordRequest passwordRequest,
            @Size(min = 24, max = 24, message = "Id must be 24 characters long")
            @PathVariable String id) {

        // hacer validaciones de tamaño especifico para password en dto PasswordRequest con anotacion jakarta

        try {
            return ResponseEntity.ok(userService.updatePassword(passwordRequest, id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // no se usa en front, sirve para hacer limpieza
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
