package com.habittracker.tracker.controllers;

import com.habittracker.tracker.ui.TerminalUI;
import com.habittracker.tracker.user.User;
import com.habittracker.tracker.user.UserRepository;
import com.habittracker.tracker.user.UserService;
import org.springframework.boot.CommandLineRunner;


import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class UserManager {
    //private ArrayList<User> users = new ArrayList<>(); // make List or set?
    //private ArrayList<Friends> friendships = new ArrayList<>();
private UserRepository repository;
private UserService userService;

private CommandLineRunner habitRunner;

static TerminalUI tui = new TerminalUI();
    public UserManager(UserRepository repository, UserService userService, CommandLineRunner habitRunner) {
        this.repository = repository;
        this.userService = userService;
        this.habitRunner = habitRunner;
    }

    public void loginMenu() throws Exception {

        String email = "";
        String password = "";

        // Loops the menu back to the console until the user chooses to exit the program.
        boolean done = false;
        while (!done) {

            tui.displayLogin();
            System.out.print("Enter option: ");

            Scanner input = new Scanner(System.in);
            char option = input.next().charAt(0);

            // Consumes \n after option
            input.nextLine();

            switch(option) {
                case '1' -> {
                    System.out.print("Enter email: ");
                    email = input.nextLine();
                }
                case '2' -> {
                    System.out.print("Enter password: ");
                    password = input.nextLine();
                }
                case '3' -> login(email, password);
                case '4' -> signupMenu();
                case 'f' -> {
                    done = true;
                    System.out.println("Exiting program...");
                }
                default -> System.out.println("Invalid input - Please try one of the menu options.");

            }
        }
    }

    private void login(String email, String password) throws Exception {
        Optional<User> optionalUser = repository.findUserByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPass().equals(password)) {
                // Credentials match an existing user
                System.out.println("call habitRunner here");
                habitRunner.run();
            } else {
                System.out.println("Invalid password. Please try again.");
            }
        } else {
            System.out.println("User not found. Please try again.");
        }
    }

    public void signupMenu() throws Exception {

        String name = "";
        String email = "";
        String pass = "";

        // Loops the menu back to the console until the user chooses to exit the program.
        boolean done = false;
        while (!done) {

            tui.displaySignUp();
            System.out.print("Enter option: ");

            Scanner input = new Scanner(System.in);
            char option = input.next().charAt(0);

            // Consumes \n after option
            input.nextLine();

            switch(option) {
                case '1' -> {
                    System.out.print("Enter name:");
                    name = input.nextLine();
                }
                case '2' -> {
                    System.out.print("Enter email: ");
                    email = input.nextLine();
                }
                case '3' -> {
                    System.out.print("Enter password: ");
                    pass = input.nextLine();
                }
                case '4' -> signup(name, email, pass);
                case '5' -> loginMenu();
                case 'f' -> {
                    done = true;
                    System.out.println("Exiting program...");
                }
                default -> System.out.println("Invalid input - Please try one of the menu options.");

            }
        }
    }

    private void signup(String name, String email, String pass) throws Exception {
        if (name.isEmpty() || pass.isEmpty() || email.isEmpty()) {
            System.out.println("Please complete all fields.");
        } else {
            User newUser = new User(name, email, pass);
            userService.addNewUser(newUser);
            loginMenu();
        }
    }


//    public void addUser(User user) {
//        users.add(user);
//    }

//    public User getUserById(int userId) {
//        for (User user : users) {
//            if (user.getUserId() == userId) {
//                return user;
//            }
//        }
//        return null; // User not found
//    }

//    public void addFriendship(Friends friends) {
//        friendships.add(friends);
//    }

    // Other methods for managing friendships

    // Other user-related methods
}
