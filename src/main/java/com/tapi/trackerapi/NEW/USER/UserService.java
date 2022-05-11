package com.tapi.trackerapi.NEW.USER;

import com.tapi.trackerapi.NEW.EXCEPTION.TResourceNotFoundException;

import java.util.List;

public interface UserService {

    public User createUser(User user);

    public User updateUser(User user, Integer userId);

    public User deleteUser(Integer userId);

    public List<User> getUsers();

    public User getUserById(Integer userId);

    User validate(User user);
}
