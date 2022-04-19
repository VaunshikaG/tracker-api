package com.tapi.trackerapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 405
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class Method_not_allowed extends RuntimeException {

    public Method_not_allowed(String message) {
        super(message);
    }
}
