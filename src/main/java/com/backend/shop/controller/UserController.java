package com.backend.shop.controller;

import com.backend.shop.DTO.RegistrationRequest;
import com.backend.shop.DTO.RegistrationResponse;
import com.backend.shop.entity.User;
import com.backend.shop.enums.UserRole;
import com.backend.shop.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

        try {
            this.userService.createUser(registrationRequest);

            RegistrationResponse registrationResponse = new RegistrationResponse();

            registrationResponse.setEmail(registrationRequest.getEmail());
            registrationResponse.setFirstName(registrationRequest.getFirstName());
            registrationResponse.setLastName(registrationRequest.getLastName());

            Map<String, Object> response = new HashMap<>();

            response.put("message", "User created successfully");
            response.put("user", registrationResponse);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(response);

        } catch (IllegalArgumentException e) {
            // Handle errors and return a JSON error response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }
}
