package com.tapi.trackerapi.EXPENSE.controller;

import com.tapi.trackerapi.EXPENSE.exception.TResourceNotFoundException;
import com.tapi.trackerapi.EXPENSE.helper.ResponseHandler;
import com.tapi.trackerapi.EXPENSE.model.User;
import com.tapi.trackerapi.EXPENSE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/expense/user")
public class UserController {

    @Autowired
    private UserService userService;

    //  register user
    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        try {
            User user1 = userService.createUser(user);
            return ResponseHandler.generateResponse("User registered successfully", HttpStatus.CREATED,
                    user1);
        } catch (Exception e) {
            return ResponseHandler.generateResult(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }


    //  get users
    @GetMapping("")
    public ResponseEntity<Object> getUsers() {
        try {
            List<User> result = userService.getUsers();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResult(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }


    //  get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@Valid @PathVariable("userId") Integer userId) throws TResourceNotFoundException {
        try {
            User user = userService.getUserById(userId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, user);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "User " + userId + " not found.");
        }
    }

    //  update by id
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Integer userId) {
        try {
            User userResponse = userService.updateUser(user, userId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, userResponse);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "User " + userId + " not found.");
        }
    }

    //  delete by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        try {
            userService.deleteUser(userId);
            return ResponseHandler.generateResult("Successfully deleted data!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "User " + userId + " not found.");
        }
    }

}
