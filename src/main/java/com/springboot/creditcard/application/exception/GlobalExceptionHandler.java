package com.springboot.creditcard.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> catchRuntimeException(RuntimeException runEx){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setMessage(runEx.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> catchException(Exception ex){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setMessage((ex.getMessage()));

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
