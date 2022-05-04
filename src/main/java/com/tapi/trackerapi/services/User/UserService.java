package com.tapi.trackerapi.services.User;

//  logic is here central part of application

import com.tapi.trackerapi.domain.User;
import com.tapi.trackerapi.exceptions.Unauthorized;

public interface UserService {

    User validateUser(String email, String password, String token) throws Unauthorized;

    User registerUser(String firstName, String lastName, String email, String password, String token) throws Unauthorized;
}
