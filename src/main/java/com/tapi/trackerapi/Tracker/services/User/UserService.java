package com.tapi.trackerapi.Tracker.services.User;

//  logic is here central part of application

import com.tapi.trackerapi.Tracker.domain.User;
import com.tapi.trackerapi.Tracker.exceptions.Unauthorized;

public interface UserService {

    User validateUser(String email, String password, String token) throws Unauthorized;

    User registerUser(String firstName, String lastName, String email, String password, String token) throws Unauthorized;
}
