package com.habittracker.tracker.habit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/habit")
@CrossOrigin
public class HabitController {

    private final HabitService habitService;

    @Autowired
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @GetMapping
    public List<Habit> getHabits(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            // If userId is provided, filter habits by userId
            return habitService.getHabitsByUserId(userId);
        } else {
            // If userId is not provided, return all habits
            return habitService.getHabits();
        }
    }

    // Send information to the database
    @PostMapping
    public void registerNewHabit(@RequestBody Habit habit) {
        habitService.addNewHabit(habit);
    }

    @DeleteMapping(path = "{habitId}")
    public void deleteHabit(@PathVariable("habitId") Long habitId) {
        habitService.deleteHabit(habitId);
    }

    // update information
    @PutMapping(path = "{habitId}")
    public void updateHabit(
            @PathVariable("habitId") Long habitId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) LocalTime reminder,
            @RequestParam(required = false) Integer streak
            ){
        habitService.updateHabit(habitId, userId, name, description, reminder, streak);
    }
}
