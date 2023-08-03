package com.edhet.store.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UniqueDatabaseFieldException extends IllegalStateException {
    public UniqueDatabaseFieldException(String error) {
        super(error);
    }
}
