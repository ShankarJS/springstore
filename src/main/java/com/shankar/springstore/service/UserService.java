package com.shankar.springstore.service;

import com.shankar.springstore.model.Role;
import com.shankar.springstore.model.User;
import com.shankar.springstore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //constructor injection(recommended)
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String name, String email, String rawPassword, Role role){
        if(userRepository.existsByEmail(email)){
            throw new RuntimeException("User with email already exists: "+email);
        }

        User user = User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .role(role)
                .build();

        return userRepository.save(user);
    }

    //find by user
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
