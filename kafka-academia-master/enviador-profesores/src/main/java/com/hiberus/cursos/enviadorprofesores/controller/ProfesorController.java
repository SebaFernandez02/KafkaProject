package com.hiberus.cursos.enviadorprofesores.controller;

import com.hiberus.cursos.enviadorprofesores.dto.ProfesorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ProfesorController {
    @Operation(summary = "Crea un profesor")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Peticion aceptada")
    })
    void crearProfesor(ProfesorDTO profesorDTO);

}
