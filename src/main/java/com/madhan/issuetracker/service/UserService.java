package com.madhan.issuetracker.service;

import org.springframework.stereotype.Service;
import com.madhan.issuetracker.model.Role;
import java.util.Optional;
import com.madhan.issuetracker.model.User;
import com.madhan.issuetracker.repository.UserRepository;

@Service
public class UserService {
    

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String email, String passwordHash, Role role){

        if(userRepository.existsByUsername(username)){
            throw new IllegalArgumentException("User already exists with username: " + username);   
        }

        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("User already exists with email: " + email);
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);           
        user.setPasswordHash(passwordHash);
        user.setRole(role);
        return userRepository.save(user);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
