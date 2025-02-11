package com.users.controller;

import com.users.dto.UserDto;
import com.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {
/*
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserSuccess() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setId(UUID.randomUUID());
        userDto.setUsername("test_user");
        userDto.setEmail("test_user@example.com");

        doNothing().when(userService).createUser(userDto);

        // Act
        ResponseEntity<UserDto> response = userController.createUser(userDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "El código de estado debería ser 201 (CREATED).");
        assertEquals(userDto, response.getBody(), "El cuerpo de la respuesta debería contener el usuario creado.");
        verify(userService, times(1)).createUser(userDto);
    }

    @Test
    void createUserBadRequest() {
        // Arrange
        UserDto userDto = new UserDto(); // Usuario incompleto para provocar un error
        userDto.setUsername("");
        userDto.setEmail("");

        doThrow(new IllegalArgumentException("Usuario inválido")).when(userService).createUser(userDto);

        // Act
        ResponseEntity<UserDto> response = userController.createUser(userDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "El código de estado debería ser 400 (BAD_REQUEST).");
        assertEquals(userDto, response.getBody(), "El cuerpo de la respuesta debería contener el usuario enviado.");
        verify(userService, times(1)).createUser(userDto);
    }*/
}
