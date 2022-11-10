package com.digitalbooking.apilodgings.exception;

import com.digitalbooking.apilodgings.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {

    private Response response;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Response response) {
        this.response = response;
    }


    public Response getResponse() {
        return response;
    }
}
