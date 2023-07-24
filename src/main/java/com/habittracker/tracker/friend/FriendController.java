package com.habittracker.tracker.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/friend")
@CrossOrigin
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping
    public List<Friend> getFriends(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            // If userId is provided, filter friends by userId
            return friendService.getFriendsById(userId);
        } else {
            // If userId is not provided, return all friends
            return friendService.getFriends();
        }
    }

    // Send information to the database
    @PostMapping
    public void registerNewFriend(@RequestBody Friend friend) {
        friendService.addNewFriend(friend);
    }

    @DeleteMapping(path = "{id}")
    public void deleteFriend(@PathVariable("id") Long friendId) {
        friendService.deleteFriend(friendId);
    }


}
