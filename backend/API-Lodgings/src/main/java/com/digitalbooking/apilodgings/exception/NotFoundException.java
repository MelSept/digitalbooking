package com.digitalbooking.apilodgings.exception;

import com.digitalbooking.apilodgings.response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

    private ResponseError response;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(ResponseError response) {
        this.response = response;
    }


    public ResponseError getResponseError() {
        return response;
    }
}
