package com.tapi.trackerapi.EXPENSE.jwt;

import com.tapi.trackerapi.EXPENSE.helper.ResponseHandler;
import com.tapi.trackerapi.EXPENSE.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(maxAge = 50000)
@RestController
public class JwtController {
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtTokenUtil jwtUtil;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/expense/user/login")
    public ResponseEntity<?> login(@RequestBody @Valid JwtRequest jwtRequest) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword()));

            UserDetails userDetails = this.customUserDetailService.loadUserByUsername(jwtRequest.getEmail());

            String accessToken = this.jwtUtil.generateToken(userDetails);

            User user = (User) authentication.getPrincipal();

            Map<String, String> map = new HashMap<>();
            map.put("userId", user.getUid().toString());
            map.put("email", userDetails.getUsername());
            map.put("token", accessToken);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, map);

        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
            return ResponseHandler.generateResponse("Invalid data!", HttpStatus.UNAUTHORIZED, ex.fillInStackTrace().getMessage());
        }
    }

    private void authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        this.authManager.authenticate(authenticationToken);
    }

}
