package br.ufrn.data.manager.controllers;

import br.ufrn.data.manager.model.exceptions.CacheCreationException;
import br.ufrn.data.manager.model.exceptions.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleException(Exception e) {
        ExceptionDetails exceptionDetails = new ExceptionDetails.Builder()
                .error("Internal server error")
                .message(e.getMessage())
                .timestamp(java.time.LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CacheCreationException.class)
    public ResponseEntity<ExceptionDetails> handleCacheCreationException(CacheCreationException e) {
        ExceptionDetails exceptionDetails = new ExceptionDetails.Builder()
                .error("Cache creation error")
                .path("/api/v1/cache")
                .message(e.getMessage())
                .timestamp(java.time.LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}