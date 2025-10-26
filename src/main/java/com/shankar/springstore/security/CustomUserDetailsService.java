package com.shankar.springstore.security;

//import com.example.eshop.model.User;
//import com.example.eshop.repository.UserRepository;
import com.shankar.springstore.model.User;
import com.shankar.springstore.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // Spring Security expects ROLE_ prefix
        var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole().name()));

//        return org.springframework.security.core.userdetails.User
//                .withUsername(u.getEmail())
//                .password(u.getPassword())
//                .authorities(authorities)
//                .accountExpired(false)
//                .accountLocked(false)
//                .credentialsExpired(false)
//                .disabled(false)
//                .build();

        return org.springframework.security.core.userdetails.User
                .withUsername(u.getEmail())
                .password(u.getPassword())
                .roles(u.getRole().name()) // 👈 Automatically adds ROLE_ prefix internally
                .build();
    }
}