package com.backend.shop.controller;

import com.backend.shop.dto.RegistrationRequest;
import com.backend.shop.dto.RegistrationResponse;
import com.backend.shop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createUser(@Validated @RequestBody RegistrationRequest registrationRequest) {

            this.userService.createUser(registrationRequest);

            RegistrationResponse registrationResponse = new RegistrationResponse(
                    registrationRequest.firstName(),
                    registrationRequest.lastName().toUpperCase(),
                    registrationRequest.email()
            );

            Map<String, Object> response = Map.of(
                    "message", "User created Successfully",
                    "user", registrationResponse
            );

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(response);

    }
}
