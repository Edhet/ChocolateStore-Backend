package com.edhet.store.exception.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFound extends IllegalStateException {
    public CategoryNotFound(String s) {
        super(s);
    }
}
