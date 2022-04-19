package com.tapi.trackerapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 404
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TCategoryNotFoundException extends RuntimeException {

    public TCategoryNotFoundException(String message) {
        super(message);
    }
}
