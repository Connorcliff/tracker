package com.habittracker.tracker.user;

import com.habittracker.tracker.habit.Habit;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity // This class is an entity which will occupy one row in the database
@Table // Specifies the details of the table associated with Habit. Same as class name
public class User {
    @Id // Sets Id as the primary key
    @SequenceGenerator( // specifies the generation strategy for primary key values.
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue( // Indicates th value of Id automatically generated by the specified generator
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id; // make a class to generate unique ids
    private String name;
    private String email;
    private String pass; // todo is password included here?

    //List<Habit> habitList = repository.getHabitList();
    //public static ArrayList<Habit> habitList = new ArrayList<>();

    //public static ArrayList<User> friendList = new ArrayList<>();

    public User() {
    }

/*    public User(Long userId,
                String name,
                String email,
                String pass,
                ArrayList habitList,
                ArrayList friendList) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.habitList = habitList;
        this.friendList = friendList;
    }

    public User(String name,
                String email,
                String pass,
                ArrayList habitList,
                ArrayList friendList) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.habitList = habitList;
        this.friendList = friendList;
    }*/
    public User(Long id, String name, String email, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return  "ID: " + getUserId() + "\n" +
                "Name: " + getName() + "\n" +
                "Email: " + getEmail() + "\n" +
                "Password: " + getPass() + "\n" +
                 "--------------------------------";
    }
}
