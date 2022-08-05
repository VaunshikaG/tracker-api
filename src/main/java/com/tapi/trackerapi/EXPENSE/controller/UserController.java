package com.tapi.trackerapi.EXPENSE.controller;

import com.tapi.trackerapi.EXPENSE.exception.TResourceNotFoundException;
import com.tapi.trackerapi.EXPENSE.helper.ResponseHandler;
import com.tapi.trackerapi.EXPENSE.model.User;
import com.tapi.trackerapi.EXPENSE.model.User;
import com.tapi.trackerapi.EXPENSE.model.UserDto;
import com.tapi.trackerapi.EXPENSE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/expense")
public class UserController {

    @Autowired
    private UserService userService;

    //  register user
    @PostMapping("/user/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto user) throws TResourceNotFoundException {
        try {
            UserDto dto = userService.createUser(user);
            System.out.println("user = " + dto.getUid());
            return ResponseHandler.generateResponse("User registered successfully", HttpStatus.CREATED, dto);
        } catch (Exception e) {
            return ResponseHandler.generateResult(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }


    //  get users
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() throws TResourceNotFoundException {
        try {
            List<UserDto> result = this.userService.getUsers();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResult(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }


    //  get user by id
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@Valid @PathVariable("userId") Integer userId) throws TResourceNotFoundException {
        try {
            UserDto dto = userService.getUserById(userId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, dto);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "User " + userId + " not found.");
        }
    }

    //  update by id
    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto User, @PathVariable Integer userId) {
        try {
            UserDto dto = userService.updateUser(User, userId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, dto);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "User " + userId + " not found.");
        }
    }

    //  delete by id
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        try {
            userService.deleteUser(userId);
            return ResponseHandler.generateResult("Successfully deleted data!", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "User " + userId + " not found.");
        }
    }

}
