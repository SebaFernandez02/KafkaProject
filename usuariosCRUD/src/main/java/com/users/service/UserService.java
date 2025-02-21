package com.users.service;

import com.users.dto.UserDto;

import java.util.List;

public interface UserService {

    void createUser(UserDto user);

    List<UserDto> getUsers();

    void modifyUser(UserDto user);

    void deleteUser(String id);

    UserDto getUser(String id);

    UserDto getUserByEmail(String email);
}
