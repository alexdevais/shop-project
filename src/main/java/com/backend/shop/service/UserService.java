package com.backend.shop.service;

import com.backend.shop.DTO.RegistrationRequest;
import com.backend.shop.entity.User;
import com.backend.shop.enums.UserRole;
import com.backend.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        System.out.println("Password: " + registrationRequest.getPassword());
        System.out.println("Confirmation Password: " + registrationRequest.getPasswordConfirmation());

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new IllegalArgumentException(("Email already in use"));
        }

        if (!registrationRequest.getPassword().equals(registrationRequest.getPasswordConfirmation())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());

        String hashedPassword = passwordEncoder.encode(registrationRequest.getPassword());
        user.setPassword(hashedPassword);

        user.setUserRole(UserRole.OWNER);


        this.userRepository.save(user);

    }


    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
