package com.habittracker.tracker.habit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/habit")
@CrossOrigin
public class HabitController {

    private final HabitService habitService;

    @Autowired
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    // Retrieve information
    @GetMapping
    public List<Habit> getHabits() {
        return habitService.getHabits();
    }

    // Send information to the database
    @PostMapping
    public void registerNewHabit(@RequestBody Habit habit) {
        habitService.addNewHabit(habit);
    }

    /** console version */
//    @DeleteMapping(path = "{habitId}")
//    public void deleteHabit(@PathVariable("habitId") String habitName) {
//        habitService.deleteHabit(habitName);
//    }

    @DeleteMapping(path = "{habitId}")
    public void deleteHabit(@PathVariable("habitId") Long habitId) {
        habitService.deleteHabit(habitId);
    }


    // update information
    @PutMapping(path = "{habitId}")
    public void updateHabit(
            @PathVariable("habitId") Long habitId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String icon,
            @RequestParam(required = false) LocalTime reminder,
            @RequestParam(required = false) Integer streak
            ){
        habitService.updateHabit(habitId, name, description, icon, reminder, streak);
    }


}
