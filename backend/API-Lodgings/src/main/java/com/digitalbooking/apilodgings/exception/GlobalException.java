package com.digitalbooking.apilodgings.exception;

import com.digitalbooking.apilodgings.response.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import java.net.ConnectException;

@RestControllerAdvice
public class GlobalException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(CannotCreateTransactionException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseError> badRequestException(CannotCreateTransactionException exception) {

        ResponseError error = new ResponseError("Internal Error");
        error.setStatusCode(500);

        if (exception.contains(ConnectException.class)) {
            logger.error("DB ConnectException: {}", exception.getMessage());
            error.setStatusCode(503);
            error.setMessage("Service Unavailable");
            return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> badRequestException(BadRequestException exception) {
        exception.getResponseError().setStatusCode(400);
        return new ResponseEntity<>(exception.getResponseError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseError> notFoundException(NotFoundException exception) {
        exception.getResponseError().setStatusCode(404);
        return new ResponseEntity<>(exception.getResponseError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseError> validationException(MethodArgumentNotValidException exception) {
        ResponseError responseError = new ResponseError("Error: Validate Fields");
        responseError.setStatusCode(400);
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String hint = String.format("Field: '%s' - Error: %s", ((FieldError) error).getField(), error.getDefaultMessage());
            responseError.addHint(hint);
        });

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<ResponseError> accessDeniedException(AccessDeniedException exception) {
        ResponseError responseError = new ResponseError(exception.getMessage());
        responseError.setStatusCode(403);

        return new ResponseEntity<>(responseError, HttpStatus.FORBIDDEN);
    }


    // Authentication Exceptions

    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseError> handleAuthenticationException(InsufficientAuthenticationException exception) {
        ResponseError responseError = new ResponseError(exception.getMessage());
        responseError.setStatusCode(401);

        return new ResponseEntity<>(responseError, HttpStatus.UNAUTHORIZED);
    }
}
