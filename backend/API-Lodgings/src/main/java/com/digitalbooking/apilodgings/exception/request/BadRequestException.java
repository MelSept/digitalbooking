package com.digitalbooking.apilodgings.exception.request;

import com.digitalbooking.apilodgings.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadRequestException extends Exception {

    private final Response responseError;

    public BadRequestException(Response responseError) {
        this.responseError = responseError;
    }


    public Response getResponseError() {
        return responseError;
    }
}
