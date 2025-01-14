package com.hiberus.cursos.consultadorclases.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
public class ClaseDTO {

    private String asignatura;
    private String nivel;
    private List<ProfesorDTO> profesores;
    private List<AlumnoDTO> alumnos;
}
