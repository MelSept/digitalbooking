package com.digitalbooking.apilodgings.jwt;

import io.jsonwebtoken.Clock;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwsClock implements Clock {

    @Override
    public Date now() {
        return new Date();
    }

    public Date getMinutes(Integer minutes){
        long seconds = minutes * 60000;
        return new Date(new Date().getTime() + seconds);
    }
}
