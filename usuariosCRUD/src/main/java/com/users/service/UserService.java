package com.users.service;

import com.users.dto.UserDto;

import java.util.List;

public interface UserService {

    void createUser(UserDto user);

    List<UserDto> getUsers();
}
