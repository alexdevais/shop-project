package com.backend.shop.service;

import com.backend.shop.dto.LoginRequest;
import com.backend.shop.entity.User;
import com.backend.shop.repository.UserRepository;
import com.backend.shop.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
/*
    private final JwtTokenUtil jwtTokenUtil;
*/


    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
  /*      this.jwtTokenUtil = jwtTokenUtil;*/
    }

/*    public String authenticate(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }


        return jwtTokenUtil.generateToken(user);
    }*/

    public Map<String, Object> authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return Map.of(
                "message", "Login successful",
                "email", user.getEmail(),
                "username", user.getUsername()
        );
    }
}
