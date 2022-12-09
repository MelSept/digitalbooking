package com.digitalbooking.apilodgings.exception;

import com.digitalbooking.apilodgings.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadRequestException extends Exception {

    private final ResponseError responseError;

    public BadRequestException(ResponseError responseError) {
        this.responseError = responseError;
    }


    public ResponseError getResponseError() {
        return responseError;
    }
}
