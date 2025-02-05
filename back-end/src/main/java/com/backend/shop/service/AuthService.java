package com.backend.shop.service;

import com.backend.shop.dto.LoginRequest;
import com.backend.shop.dto.LoginResponse;
import com.backend.shop.entity.User;
import com.backend.shop.repository.UserRepository;
import com.backend.shop.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;

    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isPasswordValid = passwordEncoder.matches(loginRequest.password(), user.getPassword());

        if (!isPasswordValid) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        System.out.println("Generated Token: " + token);

        return new LoginResponse("Login successful", token, user.getEmail(), user.getUsername());
    }
}
