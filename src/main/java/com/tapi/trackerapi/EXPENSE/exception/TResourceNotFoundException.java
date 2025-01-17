package com.tapi.trackerapi.EXPENSE.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 404
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TResourceNotFoundException extends RuntimeException {

    public TResourceNotFoundException(String message) {
        super(message);
    }
}
