package com.tapi.trackerapi.services;

import com.tapi.trackerapi.domain.User;
import com.tapi.trackerapi.exceptions.Unauthorized;
import com.tapi.trackerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User validateUser(String email, String password, String token) throws Unauthorized {
        if(email != null) email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password, String token) throws Unauthorized {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();

        //  valid email
        if(!pattern.matcher(email).matches())
            throw new Unauthorized("Invalid email format");

        //  email exists
        Integer count = userRepository.getCountByEmail(email);
        if (count > 0)
            throw new Unauthorized("Email already in use");

        //  else call repo.create method with all fields
        Integer userId = userRepository.create(firstName, lastName, email, password);
        //  and return userbyID
        return userRepository.findById(userId);
    }
}
