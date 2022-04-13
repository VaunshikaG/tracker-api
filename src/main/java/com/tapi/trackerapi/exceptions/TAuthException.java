package com.tapi.trackerapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TAuthException extends RuntimeException{

    public TAuthException(String message) {
        super(message);
    }
}
