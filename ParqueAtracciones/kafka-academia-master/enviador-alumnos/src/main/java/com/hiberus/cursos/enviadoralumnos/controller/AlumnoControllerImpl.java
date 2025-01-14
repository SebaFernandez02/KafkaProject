package com.hiberus.cursos.enviadoralumnos.controller;


import com.hiberus.cursos.enviadoralumnos.dto.AlumnoDTO;
import com.hiberus.cursos.enviadoralumnos.service.AlumnoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(value = "/alumnos")
public class AlumnoControllerImpl implements AlumnoController {

    @Autowired
    AlumnoService alumnoService;

    @Override
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public void crearAlumno(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        log.debug("Recibida peticion de crear un alumno");
        alumnoService.crear(alumnoDTO);
    }

}
