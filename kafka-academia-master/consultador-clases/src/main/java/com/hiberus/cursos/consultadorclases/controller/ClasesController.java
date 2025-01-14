package com.hiberus.cursos.consultadorclases.controller;

import com.hiberus.cursos.consultadorclases.dto.ClaseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClasesController {
    @Operation(summary = "Obtiene las clases")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Devuelve todas las clases")
    })
    ResponseEntity<List<ClaseDTO>> getClases();

}
