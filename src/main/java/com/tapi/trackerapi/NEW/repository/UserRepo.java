package com.tapi.trackerapi.NEW.repository;

import com.tapi.trackerapi.NEW.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findById(Long userId);
}
