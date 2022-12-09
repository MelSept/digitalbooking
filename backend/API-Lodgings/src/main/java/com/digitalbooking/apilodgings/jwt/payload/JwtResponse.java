package com.digitalbooking.apilodgings.jwt.payload;

public class JwtResponse {

    public Integer id;
    public String firstName;
    public String lastName;
    public String username;
    public String email;
    public String accessToken;
    public String tokenType;
    public String role;


    public JwtResponse(Integer id, String firstName, String lastName, String username, String email, String accessToken, String tokenType, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.role = role;
    }


    public JwtResponse() {

    }
}
