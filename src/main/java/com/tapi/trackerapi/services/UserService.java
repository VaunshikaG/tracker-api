package com.tapi.trackerapi.services;

//  logic is here central part of application

import com.tapi.trackerapi.domain.User;
import com.tapi.trackerapi.exceptions.TAuthException;

public interface UserService {

    User validateUser(String email, String password) throws TAuthException;

    User registerUser(String firstName, String lastName, String email, String password) throws TAuthException;
}
