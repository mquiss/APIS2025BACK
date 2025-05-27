package com.api.ecommerce.users.service;

import com.api.ecommerce.users.model.User;
import com.api.ecommerce.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service    

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsernameAndPassword(String username, String password) {
    return userRepository.findByUsernameAndPassword(username, password);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setAvatar(userDetails.getAvatar());
        user.setAddress(userDetails.getAddress());
        return userRepository.save(user);
    }



    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }    
}
