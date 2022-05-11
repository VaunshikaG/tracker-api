package com.tapi.trackerapi.NEW.USER;

import com.tapi.trackerapi.NEW.EXCEPTION.TResourceNotFoundException;
import com.tapi.trackerapi.Tracker.exceptions.Unauthorized;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
//@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User validate(User user) throws TResourceNotFoundException {
        User user1 = userRepo.findByEmail(user.getEmail());
        if(user1 == null) {
            throw new RuntimeException("User does not exist.");
        }
        if(!user.getPassword().equals(user1.getPassword())){
            throw new RuntimeException("Password mismatch.");
        }
        return user1;
    }

    @Override
    public User createUser(User user) throws TResourceNotFoundException {
        String email = user.getEmail();

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();

        //  valid email
        if(!pattern.matcher(email).matches())
            throw new Unauthorized("Invalid email format");

        //  email exists

        //  else call repo.create method with all fields
        User user1 = userRepo.save(user);
        //  and return userbyID
        return user1;
    }

    @Override
    public User updateUser(User user, Integer userId) {
        User updatedUser = new User();
        Optional<User> savedUser = userRepo.findById(userId);
        User updateUser = savedUser.get();
        if (savedUser.isPresent()) {
//            BeanUtils.copyProperties(user, updateUser, Utils.getNullPropertyNames(user));
            BeanUtils.copyProperties(user, updatedUser);
        }
        updatedUser = userRepo.save(updateUser);
        return updatedUser;
    }

    @Override
    public User deleteUser(Integer userId) {
        Optional<User> savedUser = userRepo.findById(userId);
        if (savedUser.isPresent()) {
            User deletedUser = savedUser.get();
            userRepo.delete(deletedUser);
        }
        return null;
    }

    @Override
    public List<User> getUsers() throws TResourceNotFoundException {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Integer userId) throws TResourceNotFoundException {
        User user = new User();
        Optional<User> savedUser = userRepo.findById(userId);
        if (savedUser.isPresent())
            return savedUser.get();
        return user;
    }

}
