package com.tapi.trackerapi.Tracker.repository.User;

//  related to db methods perform operations like create, delete, etc

import com.tapi.trackerapi.Tracker.domain.User;
import com.tapi.trackerapi.Tracker.exceptions.Unauthorized;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password) throws Unauthorized;

    User findByEmailAndPassword(String email, String password) throws Unauthorized;


    //  to check email exists or not for new registration
    Integer getCountByEmail(String email);


    //  to find user by id
    User findById(Integer userId);
}
