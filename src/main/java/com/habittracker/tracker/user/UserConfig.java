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

            User one = new User(
                    "Claire",
                    "1",
                    "1"
            );

            User alex = new User(
                    "Alex",
                    "alex@hotmail.com",
                    "Password1"
            );

            User james = new User(
                    "James",
                    "james@hotmail.com",
                    "NewPass"
            );

            User luca = new User(
                    "Luca",
                    "luca@hotmail.com",
                    "Pass"
            );

            repository.saveAll(
                    List.of(connor, one, alex, james, luca)
            );

//            UserManager userManager = new UserManager(repository, userService, habitRunner);
//            userManager.loginMenu();

        };
    }
}
