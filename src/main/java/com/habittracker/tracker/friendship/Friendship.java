package com.habittracker.tracker.friendship;

import jakarta.persistence.*;

@Entity // This class is an entity which will occupy one row in the database
@Table // Specifies the details of the table associated with Habit. Same as class name
public class Friendship {
    @Id // Sets Id as the primary key
    @SequenceGenerator( // specifies the generation strategy for primary key values.
            name = "friendship_sequence",
            sequenceName = "friendship_sequence",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue( // Indicates the value of Id automatically generated by the specified generator
            strategy = GenerationType.SEQUENCE,
            generator = "friendship_sequence"
    )
    private Long id;
    private Long user1Id;
    private Long user2Id;
    // Other attributes and methods

    public Friendship() {
    }

    public Friendship(Long id, Long user1Id, Long user2Id) {
        this.id = id;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }
    public Friendship(Long user1Id, Long user2Id) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Long user1Id) {
        this.user1Id = user1Id;
    }

    public Long getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Long user2Id) {
        this.user2Id = user2Id;
    }

    @Override
    public String toString() {
        return  "UserId: " + getId() + "\n" +
                "Name: " + getUser1Id() + "\n" +
                "Description: " + getUser2Id() + "\n" +
                "--------------------------------";
    }
}