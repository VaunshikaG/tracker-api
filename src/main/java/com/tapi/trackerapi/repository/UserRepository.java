package com.tapi.trackerapi.repository;

//  related to db methods perform operations like create, delete, etc

import com.tapi.trackerapi.domain.User;
import com.tapi.trackerapi.exceptions.Unauthorized;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password) throws Unauthorized;

    User findByEmailAndPassword(String email, String password) throws Unauthorized;


    //  to check email exists or not for new registration
    Integer getCountByEmail(String email);


    //  to find user by id
    User findById(Integer userId);
}
