package com.backend.shop.DTO;

public class LoginResponse {
    private String token;
    private String firstName;
    private String lastName;
    private String email;

    public LoginResponse(String token, String firstName, String lastName, String email) {
        this.token = token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
