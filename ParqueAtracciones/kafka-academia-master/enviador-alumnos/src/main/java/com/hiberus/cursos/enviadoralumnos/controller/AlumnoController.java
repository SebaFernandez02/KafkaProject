package com.hiberus.cursos.enviadoralumnos.controller;

import com.hiberus.cursos.enviadoralumnos.dto.AlumnoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface AlumnoController {
    @Operation(summary = "Crea un alumno")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Peticion aceptada")
    })
    void crearAlumno(AlumnoDTO alumnoDTO);

}
