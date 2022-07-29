package com.tapi.trackerapi.EXPENSE.jwt;

import lombok.Data;

@Data
public class JwtResponse {
    private String email;
    private String accessToken;

    public JwtResponse() { }

    public JwtResponse(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

}