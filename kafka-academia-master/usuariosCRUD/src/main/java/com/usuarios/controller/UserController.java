package com.usuarios.controller;

import com.usuarios.dto.UserDto;
import com.usuarios.service.UserService;
import javax.validation.Valid;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;


    @Operation(summary = "Crear un usuario", description = "Crea un nuevo usuario con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario creado exitosamente",
                    response = UserDto.class),
            @ApiResponse(code = 400, message = "Error en los datos de entrada",
                    response = UserDto.class)
    })
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        try {

            log.debug("Creando usuario");
            userService.createUser(user);
            log.info("Usuario creado");
            return new ResponseEntity<>(user, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }

    }
}
