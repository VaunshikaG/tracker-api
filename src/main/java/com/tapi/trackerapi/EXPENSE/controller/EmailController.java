package com.tapi.trackerapi.EXPENSE.controller;

import com.tapi.trackerapi.EXPENSE.exception.TResourceNotFoundException;
import com.tapi.trackerapi.EXPENSE.helper.ResponseHandler;
import com.tapi.trackerapi.EXPENSE.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@CrossOrigin(maxAge = 50000)
@RestController
@RequestMapping("/expense")

public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/user/otp")
    public ResponseEntity<?> sendOtp(@Valid @RequestParam("email") String email, HttpSession session) throws TResourceNotFoundException {
        try {

            //  generate otp
            Random random = new Random(100000);
            int otp = random.nextInt(999999);

            char[] one = OTP(4);
            int otp1 = Integer.parseInt(String.valueOf(one));
            String subject = "Email Verification";
            String message = "Hello ,\n\n" +
                    "This is verification email to create your account.\n" +
                    "Your OTP is " + otp1 + "\n" +
                    "If you are not asked to verify this address, you can ignore this email.\n\n" +
                    "Thank you.\n\n" +
                    "Expensify";

            boolean flag = this.emailService.sendEmail(subject, message, email);

            Date date = new Date();
            HashMap<String, String> map = new HashMap<>();
            map.put("is_successful", String.valueOf(flag));
            map.put("datetime", String.valueOf(date));
            map.put("otp", String.valueOf(otp1));

            if (flag) {
                return ResponseHandler.generateResponse("Email sent successfully", HttpStatus.OK, map);
            } else {
                session.getAttribute("Check your email id");
                return ResponseHandler.generateResponse("error", HttpStatus.BAD_REQUEST, flag);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResult(e.getMessage(), HttpStatus.MULTI_STATUS);
        }
    }
    static char[] OTP(int len) {
        System.out.println("Generating OTP using random() : ");
        System.out.print("You OTP is : ");

        // Using numeric values
        String numbers = "0123456789";

        // Using random method
        Random rndm_method = new Random();

        char[] otp = new char[len];

        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] =
                    numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return otp;
    }


}
