package com.digitalbooking.apilodgings.exception;

import com.digitalbooking.apilodgings.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class HandlerAuthenticationException extends Exception {
    private final ResponseError responseError;


    public HandlerAuthenticationException(ResponseError responseError) {
        super(responseError.getMessage());
        this.responseError = responseError;
    }

    public HandlerAuthenticationException(String message) {
        super(message);
        this.responseError = new ResponseError(message);
    }


    public ResponseError getResponseError() {
        return responseError;
    }
}
