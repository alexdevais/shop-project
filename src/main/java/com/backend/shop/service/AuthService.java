package com.backend.shop.service;

import com.backend.shop.DTO.LoginRequest;
import com.backend.shop.DTO.LoginResponse;
import com.backend.shop.entity.User;
import com.backend.shop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse authenticate(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = "jwt token";
                return new LoginResponse(token, user.getFirstName(), user.getLastName(), user.getEmail());
            }
        }
        throw new RuntimeException("invalid credentials");
    }
}
