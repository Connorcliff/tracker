package com.habittracker.tracker.habit;

import com.habittracker.tracker.controllers.AppManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class HabitConfig {

//    @Bean
//    @Order(1)
//    @DependsOn("userCommandLineRunner")
//    CommandLineRunner HabitCommandLineRunner(HabitRepository repository) {
//        return args -> {
//            Habit walk = new Habit(
//                    "Walk",
//                    "des",
//                    "i",
//                    null,
//                    4
//            );
//            repository.saveAll(
//                    List.of(walk)
//            );
//        };
//    }
    @Bean
    CommandLineRunner HabitRunner(HabitRepository repository, HabitService habitService) {
        return args -> {
            AppManager appManager = new AppManager(repository, habitService);
            appManager.home();
        };
    }
}