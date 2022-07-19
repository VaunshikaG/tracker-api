package com.tapi.trackerapi.NEW.service;

import com.tapi.trackerapi.NEW.model.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);

    public User updateUser(User user, Integer userId);

    public User deleteUser(Integer userId);

    public List<User> getUsers();

    public User getUserById(Integer userId);

    User validate(User user);
}
