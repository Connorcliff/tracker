package com.habittracker.tracker.habit;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    @Autowired
    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @GetMapping
    public List<Habit> getHabits() {
        return habitRepository.findAll();
    }

    public void addNewHabit(Habit habit) {
        Optional<Habit> habitOptional = habitRepository.findHabitByName(habit.getName());
        if (habitOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        // if email is not present then saves habit
        habitRepository.save(habit);
    }

    public void deleteHabit(String habitName) {
        Optional<Habit> habitOptional = habitRepository.findHabitByName(habitName);
        if (habitOptional.isEmpty()) {
            throw new IllegalStateException("Habit with name " + habitName + " does not exist");
        }
        habitRepository.delete(habitOptional.get());
    }


    public Optional<Habit> getHabitByName(String name) {
        return habitRepository.findHabitByName(name);
    }

    @Transactional
    public void updateHabit(Long habitId, String name, String des, String icon, LocalTime reminder, int streak) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalStateException(
                        "habit with id " + habitId + " does not exist"));

        // update name
        if (name != null && name.length() > 0 && !Objects.equals(habit.getName(), name)) {
            Optional<Habit> habitOptional = habitRepository.findHabitByName(name);
            if (habitOptional.isPresent()) {
                throw new IllegalStateException("name taken");
            }
            habit.setName(name);
        }
        // update description
        if (des != null && des.length() > 0 && !Objects.equals(habit.getDescription(), des)) {
            habit.setDescription(des);
        }
        if (icon != null && icon.length() > 0 && !Objects.equals(habit.getIcon(), icon)) {
            habit.setIcon(icon);
        }
        if (reminder != null && !Objects.equals(habit.getReminder(), reminder)) {
            habit.setReminder(reminder);
        }
        if (streak > 0) { // maybe update condition?
            habit.setStreak(streak);
        }

    }

    @Transactional
    public void increaseStreak(Long habitId, int newStreak) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new IllegalStateException(
                        "habit with id " + habitId + " does not exist"));

        if (newStreak > 0) {
            habit.setStreak(newStreak);
        }
    }
}
