package com.usuarios.controller;

import com.usuarios.dto.UserDto;
import com.usuarios.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createUser(@Valid @RequestBody UserDto user){
        log.debug("Creando usuario");
        userService.createUser(user);
    }
}
