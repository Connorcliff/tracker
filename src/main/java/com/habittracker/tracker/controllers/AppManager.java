package com.habittracker.tracker.controllers;

import com.habittracker.tracker.habit.Habit;
import com.habittracker.tracker.habit.HabitController;
import com.habittracker.tracker.habit.HabitRepository;
import com.habittracker.tracker.habit.HabitService;
import com.habittracker.tracker.ui.TerminalUI;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

import static com.habittracker.tracker.habit.Habit.iconList;
import static com.habittracker.tracker.user.User.habitList;

public class AppManager {

    private HabitRepository repository;
    private HabitService habitService;
    static TerminalUI tui = new TerminalUI();

    public AppManager(HabitRepository repository) {
        this.repository = repository;
    }

    public AppManager(HabitRepository repository, HabitService habitService) {
        this.repository = repository;
        this.habitService = habitService;
    }



    public void home() throws NullPointerException{

        // Loops the menu back to the console until the user chooses to exit the program.
        boolean done = false;
        while (!done) {

            tui.displayHomeMenu();
            System.out.print("Enter option: ");

            Scanner input = new Scanner(System.in);
            char option = input.next().charAt(0);

            // Consumes \n after option
            input.nextLine();

            switch (option) {
                case '1' -> createNewHabit();
                case '2' -> printHabitsToConsole();
                case '3' -> editHabit();
                case '4' -> increaseStreak();
                case '5' -> System.out.println("navigation menu - to implement");
                case 'f' -> {
                    done = true;
                    System.out.println("Exiting program...");
                }
                default -> System.out.println("Invalid input - Please try one of the menu options.");
            }
        }
    }

    public void createNewHabit() {

        // Create a new instance of Habit
        Habit habit = new Habit();
        String checkedName = null;
        String checkedDes = null;
        String icon = "";
        LocalTime checkedTime = null;
        int streak = 0;

        boolean done = false;
        while (!done) {

            // Prints menu to console
            tui.displayCreateHabitMenu();

            Scanner input = new Scanner(System.in);
            char option = input.next().charAt(0);

            //todo accepts more than one char

            // Consumes \n after option
            input.nextLine();

            switch (option) {
                case '1' -> {
                    System.out.print("Enter name: ");
                    String inputName = input.nextLine();
                    checkedName = checkName(inputName);

                }
                case '2' -> {
                    System.out.print("Enter description: ");
                    String inputDes = input.nextLine();
                    checkedDes = checkDescription(inputDes);
                }
                case '3' -> {
                    System.out.print("\nSelect an icon: ");
                    icon = input.nextLine();
                    //todo finish implementing

//                    addIcons();
//                    habit.getIconList();
//                    System.out.print("\nSelect an icon: ");
//                    icon = input.nextLine();
//                    habit.setIcon(icon);
                }
                case '4' -> {
                    System.out.print("Enter reminder time (in 24-hour format, HH:mm): ");
                    String inputTime = input.nextLine();
                    checkedTime = checkReminder(inputTime);
                }
                case '5' -> {
                    // Checks habit data has been provided
                    if ((Objects.equals(habit.getName(), "No name provided"))) {
                        System.out.println("Please complete all mandatory forms before saving.");
                    } else {
                        System.out.println("Saving habit...");
                        // Adds habit to the list
                        habit = new Habit(checkedName, checkedDes, icon, checkedTime, streak);
                        repository.save(habit);
                        done = true;
                        // todo accepts null input as a habit
                    }
                }
                case 'f' -> {
                    done = true;
                    System.out.println("Going back...");
                }
                default -> System.out.println("Invalid input - Please try one of the menu options.");
            }
        }
    }

    private String checkName(String name) {
        String checkedName = null;

        // Validates the user input
        if(!(name == null || name.trim().isEmpty() || name.equals("\n"))) {
            checkedName = name;
            System.out.println("Chosen name: " + name);

        } else {
            System.out.println("Invalid name entered. Please try again.");
        }
        return checkedName;
    }

    private String checkDescription(String des) {
        String checkedDes = null;

        // Validates user input
        if (des == null || des.trim().isEmpty() || des.equals("\n")) {
            checkedDes = "No description";
            System.out.println("Chosen description: " + checkedDes);

        } else {
            checkedDes = des;
            System.out.println("Chosen description: " + checkedDes);
        }
        return checkedDes;
    }

    private String checkIcon(String icon) {
        // todo implement
        return icon;
    }

    private LocalTime checkReminder(String time) {
        LocalTime checkedTime = null;

        // Validates user input
        if (time == null || time.trim().isEmpty() || time.equals("\n")) {
            checkedTime = LocalTime.parse("No reminder");
            System.out.println("No reminder set");

            // Checks user input matches HH:mm format
        } else if (!time.matches("^\\d{2}:\\d{2}$")) {
            System.out.println("Invalid input. Please try again.");

        } else {
            // Parse user input into hours and minutes
            String[] timeParts = time.split(":");
            int hours = Integer.parseInt(timeParts[0]);
            int mins = Integer.parseInt(timeParts[1]);

            // Validate the user input
            if (hours < 0 || hours > 23 || mins < 0 || mins > 59) {
                System.out.println("Invalid time. Please enter a valid time in 24-hour format.");

            } else {
                // Set the reminder
                checkedTime = LocalTime.of(hours, mins);
                System.out.println("Daily reminder set for: " + checkedTime);
            }
        }
        return checkedTime;
    }

    public void printHabitsToConsole() {
        tui.displayHabits();
        List<Habit> habits = habitService.getHabits();
        printHabitValues(habits);
    }

    private void printHabitValues(List<Habit> habits) {
        // Print the habit values to the console
        for (Habit habit : habits) {
            System.out.println("Habit Name: " + habit.getName());
            System.out.println("Description: " + habit.getDescription());
            System.out.println("Icon: " + habit.getIcon());
            System.out.println("Reminder: " + habit.getReminder());
            System.out.println("Streak: " + habit.getStreak());
            System.out.println();
        }
    }


    public void editHabit() {

        // Displays existing habits
        printHabitsToConsole();

        System.out.print("Select an existing habit: ");
        Scanner input = new Scanner(System.in);
        String habitName = input.nextLine();

        Optional<Habit> habitOptional = habitService.getHabits().stream()
                .filter(habit -> habit.getName().equalsIgnoreCase(habitName))
                .findFirst();

        if (habitOptional.isPresent()) {
            Habit habit = habitOptional.get();

            String newName = null;
            String newDes = null;
            String newIcon = null;
            LocalTime newTime = null;
            int newStreak = 0;

            // Loops the menu back to the console until the user chooses to exit the program.
            boolean done = false;
            while (!done) {

                tui.displayEditHabitMenu(habitName);

                char option = input.next().charAt(0);
                // Consumes \n after option
                input.nextLine();

                switch (option) {
                    case '1' -> {
                        System.out.print("Enter new name: ");
                        newName = input.nextLine();
                    }
                    case '2' -> {
                        System.out.print("Enter new description: ");
                        newDes = input.nextLine();
                    }
                    case '3' -> {
                        System.out.println("Available icons: " + iconList);
                        System.out.print("Select a new icon: ");
                        newIcon = input.nextLine();
                    }
                    case '4' -> {
                        System.out.print("Enter new reminder time (in 24-hour format, HH:mm): ");
                        newTime = LocalTime.parse(input.nextLine());
                    }
                    case '5' -> {
                        System.out.print("Enter new streak: ");
                        newStreak = Integer.parseInt(input.nextLine());
                    }
                    case '6' -> {
                        habitService.deleteHabit(habitName);
                        done = true;
                        System.out.println("Habit deleted. Returning to main menu.");
                    }
                    case '7' -> {
                        habitService.updateHabit(habit.getHabitId(), newName, newDes, newIcon, newTime, newStreak);
                        System.out.println("Changes saved");
                    }
                    case 'f' -> {
                        done = true;
                        System.out.println("Going back...");

                    }
                    default -> System.out.println("Invalid input - Please try one of the menu options.");
                }
            }

        } else {
            System.out.println("Habit not found. Please try again");
        }
    }


/*
    public static void findIcon(String userInput) {

        // Search for the icon
        Icon selectedIcon = null;
        for (Icon icon : iconList) {
            if (icon.getIconName().equalsIgnoreCase(userInput)) {
                selectedIcon = icon;
                break;
            }
        }
        h.setIcon(selectedIcon);
    }*/

    public void increaseStreak() {
        // Display existing habits
        printHabitsToConsole();

        // Prompt the user to select an existing habit
        System.out.print("Select an existing habit: ");
        Scanner input = new Scanner(System.in);
        String habitName = input.nextLine();

        // Find the habit with the specified name
        Optional<Habit> habitOptional = habitService.getHabits().stream()
                .filter(habit -> habit.getName().equalsIgnoreCase(habitName))
                .findFirst();

        if (habitOptional.isPresent()) {
            Habit habit = habitOptional.get();

            // increment the current streak by 1
            int newStreak = habit.getStreak();
            newStreak++;

            // Update the habit's streak value
            habitService.increaseStreak(habit.getHabitId(), newStreak);
            System.out.println("Streak increased by 1!");
        } else {
            System.out.println("Habit not found. Please try again.");
        }
    }


//    public static void increaseStreak() {
//        tui.displayHabits();
//
//        System.out.print("Which streak should we increase: ");
//        Scanner input = new Scanner(System.in);
//        String habitName = input.nextLine();
//
//        // Find the habit with the specified name
//        Habit habitToEdit = null;
//        for (Habit habit : habitList) {
//            if (habit.getName().equalsIgnoreCase(habitName)) {
//                habitToEdit = habit;
//                break;
//            }
//        }
//
//        if (habitToEdit != null) {
//
//            int currentStreak =  habitToEdit.getStreak();
//            currentStreak++;
//            habitToEdit.setStreak(currentStreak);
//            System.out.println("Streak increased by 1.");
//
//            //todo make this only available once a day
//
//        } else {
//            System.out.println("No habit found. Please try again.");
//        }
//
//    }
}
