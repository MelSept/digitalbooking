package com.digitalbooking.apilodgings.controller;

import com.digitalbooking.apilodgings.dto.user.UserDTO;
import com.digitalbooking.apilodgings.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final IUserService userService;


    @Autowired
    public UserController(@Qualifier("UserServiceImpl") IUserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        UserDTO userSaved = userService.createUser(userDTO);

        return new ResponseEntity<>(userSaved, headers, HttpStatus.CREATED);
    }

    @GetMapping(path = {"/"})
    public ResponseEntity<List<UserDTO>> findAllProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        List<UserDTO> categoriesFound = userService.findAllUsers();

        return new ResponseEntity<>(categoriesFound, headers, HttpStatus.OK);
    }
}
