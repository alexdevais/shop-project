package com.backend.shop.dto;

public record LoginResponse(String message, String token, String email, String username) {}