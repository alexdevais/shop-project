package com.backend.shop.service;

import com.backend.shop.dto.RegistrationRequest;
import com.backend.shop.entity.User;
import com.backend.shop.enums.UserRole;
import com.backend.shop.exceptions.DuplicateEmailException;
import com.backend.shop.exceptions.PasswordMismatchException;
import com.backend.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(RegistrationRequest registrationRequest) {

        if (userRepository.existsByEmail(registrationRequest.email())) {
            throw new DuplicateEmailException(("Email already in use"));
        }

        if (!registrationRequest.password().equals(registrationRequest.passwordConfirmation())) {
            throw new PasswordMismatchException("Passwords do not match");
        }

        User user = new User();
        user.setFirstName(registrationRequest.firstName());
        user.setLastName(registrationRequest.lastName().toUpperCase());
        user.setEmail(registrationRequest.email());

        String hashedPassword = passwordEncoder.encode(registrationRequest.password());
        user.setPassword(hashedPassword);

        user.setUserRole(UserRole.USER);

        this.userRepository.save(user);

    }
}
