package com.digitalbooking.apilodgings.service.user;

import com.digitalbooking.apilodgings.dto.user.UserDTO;

import java.util.List;

public interface IUserService {

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> findAllUsers();

}
