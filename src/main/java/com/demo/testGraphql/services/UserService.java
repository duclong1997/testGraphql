package com.demo.testGraphql.services;

import com.demo.testGraphql.models.dtos.RegisterUser;
import com.demo.testGraphql.models.dtos.UserDto;

public interface UserService {
    UserDto authen(String username, String password);

    UserDto getMe();

    UserDto register(RegisterUser userRegister);
}
