package com.suscriptions.controller;

import com.suscriptions.dto.SuscriptionDto;
import com.suscriptions.service.SuscriptionService;
import javax.validation.Valid;
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
