package com.tapi.trackerapi.NEW.controller;

import com.tapi.trackerapi.NEW.Constants;
import com.tapi.trackerapi.NEW.ResponseHandler;
import com.tapi.trackerapi.NEW.exception.TResourceNotFoundException;
import com.tapi.trackerapi.NEW.model.User;
import com.tapi.trackerapi.NEW.repository.UserRepo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    //  login user
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user) {
        try {
            User result = userRepo.save(user);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    //  get user
    @GetMapping("userId")
    public ResponseEntity<Object> getAllUser() {
        try {
            List<User> result = userRepo.findAll();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    //  get user_by_id
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser_by_id(@PathVariable("userId") Long userId) throws TResourceNotFoundException {
        try {
            User user = userRepo.findById(userId);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, user);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //  to generate jwt token
    private Map<String, String> generateToken(com.tapi.trackerapi.Tracker.domain.User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();
        //  compact builds token & stores it in variable "token"
        Map<String, String> map = new HashMap<>();
        //  adding parameters to api response
        map.put("userId", user.getUserId().toString());
        map.put("email", user.getEmail());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("token", token);
        return map;
    }

}
