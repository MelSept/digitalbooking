package com.digitalbooking.apilodgings.exception;

import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    // ResponseEntityExceptionHandler

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> badRequestException(BadRequestException exception) {

        return new ResponseEntity<>((ResponseError) exception.getResponseError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> notFoundException(NotFoundException exception) {

        return new ResponseEntity<>(exception.getResponse(), HttpStatus.NOT_FOUND);
    }
}
