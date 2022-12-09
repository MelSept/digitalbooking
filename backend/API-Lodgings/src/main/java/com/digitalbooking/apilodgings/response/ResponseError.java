package com.digitalbooking.apilodgings.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseError {

    private String message;
    private Integer statusCode;
    private final List<String> hints;

    public ResponseError(String message) {
        this.message = message;
        this.hints = new ArrayList<>();
    }

    public ResponseError() {
        this.hints = new ArrayList<>();
    }

    public void addHint(String message){
        hints.add(message);
    }
}
