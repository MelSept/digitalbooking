package com.digitalbooking.apilodgings.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {

    private final String message;

    public Response(String message) {
        this.message = message;
    }

    public Response() {
        message = "";
    }
}
