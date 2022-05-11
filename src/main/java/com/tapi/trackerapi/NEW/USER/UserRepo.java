package com.tapi.trackerapi.NEW.USER;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

//    Integer getCountByEmail(String email);

    User findByEmail(String email);

}
