package com.tapi.trackerapi.EXPENSE.service;

import com.tapi.trackerapi.EXPENSE.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User createUser(User user);

    public User updateUser(User user, Integer userId);

    void deleteUser(Integer userId);

    public List<User> getUsers();

    public User getUserById(Integer userId);

    Optional<User> validate(User user);
}
