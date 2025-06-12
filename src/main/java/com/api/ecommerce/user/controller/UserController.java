package com.api.ecommerce.user.controller;

import com.api.ecommerce.user.model.User;
import com.api.ecommerce.user.service.UserService;
import com.api.ecommerce.user.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// TODO:
// - export const fetchAllUsers = () => api.get('/users');
// - export const fetchUserById = (id) => api.get(`/users/${id}`);
// - export const updateUsername = (id, newUsername) => api.patch(`/users/${id}`, { username: newUsername });
// - export const updateFirstName = (id, newFirstName) => api.patch(`/users/${id}`, { firstName: newFirstName });
// - export const updateLastName = (id, newLastName) => api.patch(`/users/${id}`, { lastName: newLastName });
// - export const updateAddress = (id, newAddress) => api.patch(`/users/${id}`, { address: newAddress });
// - export const updateAvatar = (id, newAvatar) => api.patch(`/users/${id}`, { avatar: newAvatar });
// - export const updateEmail = (id, newEmail) => api.patch(`/users/${id}`, { email: newEmail });
// - export const updatePassword = (id, newPassword) => api.patch(`/users/${id}`, { password: newPassword });

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try{
            Optional<UserDTO> user = userService.getUserById(id);
            if (user.isPresent()) {
                
                return ResponseEntity.ok(user.get());
            }
                else {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
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
