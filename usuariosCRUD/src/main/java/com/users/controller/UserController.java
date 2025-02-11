package com.users.controller;

import com.users.dto.UserDto;
import com.users.service.UserService;
import javax.validation.Valid;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> getUsers(){
        try {
            log.info("try");
            List<UserDto> userDtos = userService.getUsers();
            log.info("tengo la lista");
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
