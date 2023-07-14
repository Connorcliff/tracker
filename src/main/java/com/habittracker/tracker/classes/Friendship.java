package com.habittracker.tracker.classes;

public class Friendship {

    private Long Id;
    private Long user1Id;
    private Long user2Id;
    // Other attributes and methods

    public Friendship(Long user1Id, Long user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        // Initialize other attributes
    }
}
