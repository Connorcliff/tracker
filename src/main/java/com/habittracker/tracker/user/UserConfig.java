package com.habittracker.tracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import com.habittracker.tracker.controllers.UserManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean

    CommandLineRunner userRunner(UserRepository repository, UserService userService, CommandLineRunner habitRunner) {
        return args -> {
            User connor = new User(
                    "Connor",
                    "connor@gmail.com",
                    "Pass123"

            );

            User alex = new User(
                    "Alex",
                    "alex@hotmail.com",
                    "Password1"

            );

            repository.saveAll(
                    List.of(connor, alex)
            );

            UserManager userManager = new UserManager(repository, userService, habitRunner);
            userManager.loginMenu();

        };
    }
}
