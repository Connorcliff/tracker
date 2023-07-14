package com.habittracker.tracker.habit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    // from amigos code
    // SELECT * FROM habit WHERE email = ?
    @Query("SELECT h FROM Habit h WHERE h.name = ?1")
    Optional<Habit> findHabitByName(String name);

}
