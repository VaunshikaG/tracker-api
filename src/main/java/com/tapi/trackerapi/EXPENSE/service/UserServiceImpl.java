package com.tapi.trackerapi.EXPENSE.service;

import com.tapi.trackerapi.EXPENSE.exception.TResourceNotFoundException;
import com.tapi.trackerapi.EXPENSE.model.User;
import com.tapi.trackerapi.EXPENSE.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
//@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(User user) throws TResourceNotFoundException {
        User user1 = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pswd = passwordEncoder.encode(user.getPassword());

        //  email exists
        if (userRepo.findByEmail(user.getEmail()).isPresent())
            throw new TResourceNotFoundException("Email already in use");

        user1.setEmail(user.getEmail());
        user1.setPassword(pswd);
        return userRepo.save(user1);
    }

    @Override
    public User updateUser(User user, Integer userId) {

        userRepo.findById(userId)
                .orElseThrow(()-> new TResourceNotFoundException("User Id not found"));
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        User updatedUser=this.userRepo.save(user);
        return updatedUser;

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
    public List<User> getUsers() throws TResourceNotFoundException {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Integer userId) throws TResourceNotFoundException {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new TResourceNotFoundException("User Id not found"));
        return user;
    }

}
