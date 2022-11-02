package com.digitalbooking.apilodgings.exception.request;

import com.digitalbooking.apilodgings.response.Response;
import com.digitalbooking.apilodgings.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> missingPathVariableException
            (MissingPathVariableException exception) {

        ResponseError response = new ResponseError("Invalid Operation: Not");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ResponseError> methodNotSupportedException
            (HttpRequestMethodNotSupportedException exception, WebRequest request) {

        ResponseError response = new ResponseError("Invalid Operation: Not");
        response.addHint(exception.getMethod());
        response.addHint(request.getContextPath());

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
