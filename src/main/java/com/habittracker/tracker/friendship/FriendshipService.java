package com.habittracker.tracker.friendship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class FriendshipService {

        private final com.habittracker.tracker.friendship.FriendshipRepository friendshipRepository;

        @Autowired
        public FriendshipService(com.habittracker.tracker.friendship.FriendshipRepository friendshipRepository) {
            this.friendshipRepository = friendshipRepository;
        }

        @GetMapping
        public List<Friendship> getFriendships() {
            return friendshipRepository.findAll();
        }

        public void addNewFriendship(Friendship friendship) {
//        Optional<Friendship> friendshipOptional = friendshipRepository.findFriendshipByName(friendship.getName());
//        if (friendshipOptional.isPresent()) {
//            throw new IllegalStateException("Email taken");
//        }
            // if email is not present then saves friendship
            friendshipRepository.save(friendship);
        }

        public void deleteFriendship(Long friendshipId) {
            boolean exists = friendshipRepository.existsById(friendshipId);
            if (!exists) {
                throw new IllegalStateException(
                        "Friendship with id " + friendshipId + " does not exist");
            }
            friendshipRepository.deleteById(friendshipId);
        }

        // get friendships by userId
        public List<Friendship> getFriendshipsById(Long id) {
            return friendshipRepository.findFriendshipsById(id);
        }

    }

