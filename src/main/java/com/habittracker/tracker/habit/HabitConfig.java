package com.habittracker.tracker.habit;

import com.habittracker.tracker.controllers.AppManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HabitConfig {

    @Bean
    CommandLineRunner HabitCommandLineRunner(HabitRepository repository, HabitService habitService) {
        return args -> {
            AppManager appManager = new AppManager(repository, habitService);
            appManager.home();
        };
    }
}