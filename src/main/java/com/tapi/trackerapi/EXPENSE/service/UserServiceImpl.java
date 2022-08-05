package com.tapi.trackerapi.EXPENSE.service;

import com.tapi.trackerapi.EXPENSE.exception.TResourceNotFoundException;
import com.tapi.trackerapi.EXPENSE.model.User;
import com.tapi.trackerapi.EXPENSE.model.UserDto;
import com.tapi.trackerapi.EXPENSE.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
//@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) throws TResourceNotFoundException {
        User user1 = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pswd = passwordEncoder.encode(userDto.getPassword());

        //  email exists
        if (userRepo.findByEmail(userDto.getEmail()).isPresent())
            throw new TResourceNotFoundException("Email already in use");

        user1.setEmail(userDto.getEmail());
        user1.setPassword(pswd);
        User newUser = userRepo.save(user1);
        return this.modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new TResourceNotFoundException("User Id not found"));
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        User updatedUser = this.userRepo.save(user);
        return this.modelMapper.map(updatedUser, UserDto.class);

//        User updatedUser = new User();
//        Optional<User> savedUser = userRepo.findById(userId);
//        User updateUser = savedUser.get();
//        if (savedUser.isPresent()) {
//            BeanUtils.copyProperties(user, updatedUser);
//        }
//        updatedUser = userRepo.save(updateUser);
//        return updatedUser;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new TResourceNotFoundException("User Id not found"));
        userRepo.delete(user);
    }

    @Override
    public List<UserDto> getUsers() throws TResourceNotFoundException {

        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos =
                this.userRepo.findAll().stream().map(user -> modelMapper.map(user, UserDto.class))
                        .collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public UserDto getUserById(Integer userId) throws TResourceNotFoundException {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new TResourceNotFoundException("User Id not found"));
        return this.modelMapper.map(user, UserDto.class);
    }

}
