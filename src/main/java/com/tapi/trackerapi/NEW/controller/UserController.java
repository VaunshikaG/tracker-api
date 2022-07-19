package com.tapi.trackerapi.NEW.controller;

import com.tapi.trackerapi.NEW.HELPER.Constants;
import com.tapi.trackerapi.NEW.HELPER.ResponseHandler;
import com.tapi.trackerapi.NEW.EXCEPTION.TResourceNotFoundException;
import com.tapi.trackerapi.NEW.model.User;
import com.tapi.trackerapi.NEW.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/tracker/user")
public class UserController {

    @Autowired
    private UserService userService;

    //  login user
    @PostMapping("/login")
    //  ResponseEntity is extension of http entity through this we can also control status code, headers, etc
    public ResponseEntity<Object> loginUser(@RequestBody User user) {
        try {
            User user1 = userService.validate(user);
            return ResponseHandler.generateResponse("User login successfully", HttpStatus.OK, generateToken(user1));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, e.getMessage());
        }
    }


    //  register user
    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            User user1 = userService.createUser(user);
            return ResponseHandler.generateResponse("User registered successfully", HttpStatus.CREATED,
                    user);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    //  get user
    @GetMapping("")
    public ResponseEntity<Object> getUsers() {
        try {
            List<User> result = userService.getUsers();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    //  get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") Integer userId) throws TResourceNotFoundException {
        try {
            User user = userService.getUserById(userId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, user);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //  update by id
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable("userId") Integer userId) {
        User userResponse = userService.updateUser(user, userId);
        return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, userResponse);
    }

    //  delete by id
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId) {

        User user = userService.deleteUser(userId);
        return ResponseHandler.generateResponse("Successfully deleted data!", HttpStatus.OK, user);
    }

    //  to generate jwt token
    private Map<String, String> generateToken(User user) {
        Long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .claim("password", user.getPassword())
                .compact();
        //  compact builds token & stores it in variable "token"
        Map<String, String> map = new HashMap<>();
        //  adding parameters to api response
        map.put("userId", String.valueOf(user.getUserId()));
        map.put("email", user.getEmail());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("password", user.getPassword());
        map.put("token", token);
        return map;
    }

}
