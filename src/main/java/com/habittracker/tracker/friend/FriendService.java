package com.habittracker.tracker.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Service
public class FriendService {

        private final com.habittracker.tracker.friend.FriendRepository friendRepository;

        @Autowired
        public FriendService(com.habittracker.tracker.friend.FriendRepository friendRepository) {
            this.friendRepository = friendRepository;
        }

        @GetMapping
        public List<Friend> getFriends() {
            return friendRepository.findAll();
        }

        public void addNewFriend(Friend friend) {
//        Optional<Friend> friendOptional = friendRepository.findFriendByName(friend.getName());
//        if (friendOptional.isPresent()) {
//            throw new IllegalStateException("Email taken");
//        }
            // if email is not present then saves friend
            friendRepository.save(friend);
        }

        public void deleteFriend(Long friendId) {
            boolean exists = friendRepository.existsById(friendId);
            if (!exists) {
                throw new IllegalStateException(
                        "Friend with id " + friendId + " does not exist");
            }
            friendRepository.deleteById(friendId);
        }

        // get friends by userId
        public List<Friend> getFriendsById(Long id) {
            return friendRepository.findFriendsById(id);
        }

    }

