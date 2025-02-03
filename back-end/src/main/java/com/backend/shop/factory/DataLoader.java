package com.backend.shop.factory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserFactory userFactory;

    public DataLoader(UserFactory userFactory) {
        this.userFactory = userFactory;
    }


    @Override
    public void run(String... args) throws Exception {
        userFactory.createUsers(10);
    }
}
