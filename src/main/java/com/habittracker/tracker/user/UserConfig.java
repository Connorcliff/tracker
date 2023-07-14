package com.habittracker.tracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository) {
        return args -> {
            User connor = new User(
                    "Connor",
                    "connor@gmail.com",
                    "Pass123"

            );

            User alex = new User(
                    "Alex",
                    "alex@gmail.com",
                    "Pass123"

            );

            repository.saveAll(
                    List.of(connor, alex)
            );
        };
    }
}
