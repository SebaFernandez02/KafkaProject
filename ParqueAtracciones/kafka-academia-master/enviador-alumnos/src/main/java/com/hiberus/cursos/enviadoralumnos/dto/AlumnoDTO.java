package com.hiberus.cursos.enviadoralumnos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO {

    private String identificador;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String dniTutor;
    private int movilTutor;
    private String asignatura;
    private String nivel;

}
