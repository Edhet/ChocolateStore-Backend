package com.edhet.store.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> customExceptionHandler(Exception exception) {
        int statusCode = exception.getClass().getAnnotation(ResponseStatus.class).value().value();
        ErrorDTO errorMessage = new ErrorDTO(LocalDateTime.now(), statusCode, exception.getMessage());
        return ResponseEntity.status(statusCode).body(errorMessage);
    }
}
