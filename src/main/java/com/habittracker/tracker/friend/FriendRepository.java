package com.habittracker.tracker.friend;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    // Fetch all friends for a specific user (user1id or user2id)
    @Query("SELECT f FROM Friend f WHERE f.user1Id = ?1 OR f.user2Id = ?1")
    List<Friend> findFriendsById(Long id);

}
