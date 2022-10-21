package com.tapi.trackerapi.EXPENSE.controller;

import com.tapi.trackerapi.EXPENSE.exception.TResourceNotFoundException;
import com.tapi.trackerapi.EXPENSE.helper.ResponseHandler;
import com.tapi.trackerapi.EXPENSE.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@RestController
@RequestMapping("/expense")

public class OtpController {

    @PostMapping("/user/otp")
    public ResponseEntity<?> sendOtp(@Valid @RequestParam("email") String email) throws TResourceNotFoundException {
        try {
            System.out.println("email = " + email);

//            generate otp
            Random random = new Random(10000);
            int otp = random.nextInt(99999);
            System.out.println("otp = " + otp);

            return ResponseHandler.generateResponse("verify otp", HttpStatus.CREATED, otp);
//            return ResponseHandler.generateResponse("User registered successfully", HttpStatus.CREATED, otp);
        } catch (Exception e) {
            return ResponseHandler.generateResult(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }

}
