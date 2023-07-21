package com.habittracker.tracker.habit;

import com.habittracker.tracker.controllers.AppManager;
import com.habittracker.tracker.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
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
            Habit habit0 = new Habit(
                    2L,
                    "Habit ID 0",
                    "yoyoyo",
                    LocalTime.of(11, 11),
                    5
            );

            Habit habit1 = new Habit(
                    2L,
                    "Habit ID 1",
                    "hello",
                    LocalTime.of(18, 10),
                    6
            );

            Habit habit2 = new Habit(
                    1L,
                    "Habit ID 2",
                    "world",
                    LocalTime.of(10, 55),
                    8
            );

            Habit habit3 = new Habit(
                    2L,
                    "Habit ID 3",
                    "my",
                    LocalTime.of(10, 55),
                    10
            );
            Habit habit4 = new Habit(
                    1L,
                    "Habit ID 4",
                    "house",
                    LocalTime.of(10, 55),
                    45
            );

            repository.saveAll(
                    List.of(habit0, habit1, habit2, habit3, habit4)
            );


            AppManager appManager = new AppManager(repository, habitService);
            appManager.home();
        };
    }
}