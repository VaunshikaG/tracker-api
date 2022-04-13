package com.tapi.trackerapi.repository;

//  related to db methods perform operations like create, delete, etc

import com.tapi.trackerapi.domain.User;
import com.tapi.trackerapi.exceptions.TAuthException;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password) throws TAuthException;

    User findByEmailAndPassword(String email, String password) throws TAuthException;


    //  to check email exists or not for new registration
    Integer getCountByEmail(String email);


    //  to find user by id
    User findById(Integer userId);
}
