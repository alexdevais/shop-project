package com.backend.shop.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirmation;
}
