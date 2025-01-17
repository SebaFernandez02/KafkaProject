package com.usuarios.controller;

import com.usuarios.dto.SuscriptionDto;
import com.usuarios.service.SuscriptionService;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/suscriptions")
public class SuscriptionController {
    @Autowired
    private SuscriptionService suscriptionService;


    @PostMapping
    public ResponseEntity<SuscriptionDto> createSuscription(@Valid @RequestBody SuscriptionDto suscriptionDto){
        try {
            log.debug("Creando suscripcion");
            suscriptionService.createSuscription(suscriptionDto);
            log.info("Suscripcion creada");
            return new ResponseEntity<>(suscriptionDto, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(suscriptionDto, HttpStatus.BAD_REQUEST);
        }

    }
}
