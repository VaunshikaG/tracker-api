package com.tapi.trackerapi.EXPENSE.service;

import com.tapi.trackerapi.EXPENSE.model.User;
import com.tapi.trackerapi.EXPENSE.model.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer userId);

    void deleteUser(Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getUsers();

}
