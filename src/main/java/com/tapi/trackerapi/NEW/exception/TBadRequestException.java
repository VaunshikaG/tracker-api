package com.tapi.trackerapi.NEW.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//  400
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TBadRequestException extends RuntimeException{

    public TBadRequestException(String message) {
        super(message);
    }
}
