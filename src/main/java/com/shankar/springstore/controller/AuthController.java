package com.shankar.springstore.controller;

import com.shankar.springstore.dto.AuthRequest;
import com.shankar.springstore.dto.AuthResponse;
import com.shankar.springstore.dto.RegisterRequest;
import com.shankar.springstore.model.Role;
import com.shankar.springstore.model.User;
import com.shankar.springstore.security.JwtUtil;
import com.shankar.springstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest req) {
        // default new users as USER; change as needed
        User user = userService.registerUser(req.name(), req.email(), req.password(), Role.USER);

        String token = jwtUtil.generateToken(
                user.getEmail(),
                Map.of("role", "ROLE_" + user.getRole().name(), "name", user.getName())
        );
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );

        // principal username is the email (configured in CustomUserDetailsService)
        String email = auth.getName();

        String token = jwtUtil.generateToken(
                email,
                Map.of("role", auth.getAuthorities().iterator().next().getAuthority())
        );
        return new AuthResponse(token);
    }
}
