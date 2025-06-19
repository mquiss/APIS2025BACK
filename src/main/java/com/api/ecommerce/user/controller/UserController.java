package com.api.ecommerce.user.controller;

import com.api.ecommerce.user.dto.*;
import com.api.ecommerce.user.model.User;
import com.api.ecommerce.user.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    // TODO: crear metodo para encriptar contraseñas y usar en updatePassword del service
    @PatchMapping("/{id}/password")
    public ResponseEntity<UserResponse> updatePassword(
            @Valid @RequestBody PasswordRequest passwordRequest,
            @Size(min = 24, max = 24, message = "id must be 24 characters long")
            @PathVariable String id) {

        if (passwordRequest.getNewPassword() == null || passwordRequest.getNewPassword().length() < 6) {
            return ResponseEntity.badRequest().build(); // o devolver un error más descriptivo
        }

        UserResponse updatedUser = userService.updatePassword(passwordRequest, id);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User userDetails) {
       try{

           return userService.updateUser(id, userDetails)!=null
               ? ResponseEntity.ok(userDetails)
               : ResponseEntity.status(404).body("Usuario no encontrado");

       }catch (Exception e) {
           return ResponseEntity.status(500).body("Error: " + e.getMessage());
       }
       
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
