package com.tapi.trackerapi.resources;

import com.tapi.trackerapi.Constants;
import com.tapi.trackerapi.domain.User;
import com.tapi.trackerapi.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResources {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String token = (String) userMap.get("token");
        User user = userService.validateUser(email, password, token);

        //  before JWT token
        //  Map<String, String> map = new HashMap<>();
        //  map.put("message", "Login successful");
        //  return new ResponseEntity<>(map, HttpStatus.OK);

        Map<String, String> map = new HashMap<>();
        map.put("email", user.getFirstName());
        map.put("password", user.getPassword());
        //  before JWT token
        return new ResponseEntity<>(generateToken(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    //  ResponseEntity is extension of http entity through this we can also control statuscode, headers, etc
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> userMap) {
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        User user = userService.registerUser(firstName, lastName, email, password);

        //  before JWT token
        //  Map<String, String> map = new HashMap<>();
        //  map.put("message", "User registered successfully");
        //  return new ResponseEntity<>(map, HttpStatus.OK);


        //  before JWT token

        return new ResponseEntity<>(generateToken(user), HttpStatus.OK);
    }

    //  to generate jwt token
    private Map<String, String> generateToken(User user) {
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
