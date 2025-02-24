package com.backend.shop.factory;

import com.backend.shop.entity.User;
import com.backend.shop.enums.UserRole;
import com.backend.shop.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserFactory {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Faker faker = new Faker();
    private final Set<String> generatedEmails = new HashSet<>();

    public void createUser() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName().toUpperCase();
        String email = generateUniqueEmail();
        String password = this.passwordEncoder.encode(faker.internet().password());

        User user = new User(firstName, lastName, email, password);
        user.setUserRole(UserRole.USER);

        userRepository.save(user);
    }

    private String generateUniqueEmail() {
        String email;
        do {
            email = faker.internet().emailAddress();
        } while (generatedEmails.contains(email) || userRepository.existsByEmail(email));

        generatedEmails.add(email);
        return email;
    }

    public void createUsers(Integer count) {
        for (int i = 0; i < count; i++) {
            createUser();
        }
    }
}
