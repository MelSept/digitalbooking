package com.digitalbooking.apilodgings.response;

import lombok.Getter;

@Getter
public class Response {

    private final String message;

    public Response(String message) {
        this.message = message;
    }
}
