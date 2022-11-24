package com.digitalbooking.apilodgings.service.user;

import com.digitalbooking.apilodgings.dto.user.UserDTO;
import com.digitalbooking.apilodgings.entity.User;
import com.digitalbooking.apilodgings.repository.IUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "UserServiceImpl")
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final ObjectMapper mapper;


    @Autowired
    public UserServiceImpl(@Qualifier("IUserRepository") IUserRepository userRepository) {
        this.userRepository = userRepository;
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {

        return null;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> usersFound = userRepository.findAll();
        List<UserDTO> usersDTOFound = new ArrayList<>();

        for (User user : usersFound) {
            usersDTOFound.add(mapper.convertValue(user, UserDTO.class));
        }

        return usersDTOFound;
    }
}
