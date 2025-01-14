package com.hiberus.cursos.enviadorprofesores.controller;


import com.hiberus.cursos.enviadorprofesores.dto.ProfesorDTO;
import com.hiberus.cursos.enviadorprofesores.service.ProfesorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(value = "/profesores")
public class ProfesorControllerImpl implements ProfesorController {

    @Autowired
    ProfesorService profesorService;

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public void crearProfesor(@Valid @RequestBody ProfesorDTO profesorDTO) {
        log.debug("Recibida peticion de crear un profesor");
        profesorService.crear(profesorDTO);
    }

}
