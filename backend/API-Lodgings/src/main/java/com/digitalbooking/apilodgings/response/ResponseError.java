package com.digitalbooking.apilodgings.response;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResponseError extends Response {

    private final List<String> hints;

    public ResponseError(String message) {
        super(message);
        this.hints = new ArrayList<>();
    }


    public void addHint(String message){
        hints.add(message);
    }
}
