package com.backend.shop.controller;

import com.backend.shop.dto.LoginRequest;
import com.backend.shop.dto.LoginResponse;
import com.backend.shop.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> response = authService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }
}
