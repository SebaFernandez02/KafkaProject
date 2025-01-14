package com.usuarios.controller;

import com.usuarios.dto.SuscriptionDto;
import com.usuarios.service.SuscriptionService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/suscriptions")
public class SuscriptionController {
    @Autowired
    private SuscriptionService suscriptionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createUser(@Valid @RequestBody SuscriptionDto suscriptionDto){
        log.debug("Creando suscripcion");
        suscriptionService.createSuscription(suscriptionDto);
    }
}
