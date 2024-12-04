package com.backend.shop.DTO;


import lombok.Data;

@Data
public class RegistrationResponse {
    private String firstName;
    private String lastName;
    private String email;

    public RegistrationResponse(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public RegistrationResponse() {
    }
}
