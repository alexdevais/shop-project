package com.backend.shop.dto;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String passwordConfirmation
) {}
