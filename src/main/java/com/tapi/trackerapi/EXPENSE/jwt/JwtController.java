package com.tapi.trackerapi.EXPENSE.jwt;

import javax.validation.Valid;

import com.tapi.trackerapi.EXPENSE.helper.ResponseHandler;
import com.tapi.trackerapi.EXPENSE.model.User;
import com.tapi.trackerapi.EXPENSE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class JwtController {
    @Autowired AuthenticationManager authManager;
    @Autowired JwtTokenUtil jwtUtil;

    @Autowired
    UserService userService;

    @PostMapping("/expense/login")
    public ResponseEntity<?> login(@RequestBody @Valid JwtRequest jwtRequest) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword()));

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateToken(user);

            Map<String, String> map = new HashMap<>();
            map.put("userId", user.getUid().toString());
            map.put("email", user.getEmail());
            map.put("token", accessToken);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, map);

        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
            return ResponseHandler.generateResponse("Invalid data!", HttpStatus.UNAUTHORIZED, ex.fillInStackTrace().getMessage());
        }
    }

}
