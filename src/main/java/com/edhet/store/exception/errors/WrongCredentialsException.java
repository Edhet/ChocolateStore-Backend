package com.edhet.store.exception.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongCredentialsException extends IllegalArgumentException {
    public WrongCredentialsException(String s) {
        super(s);
    }
}
