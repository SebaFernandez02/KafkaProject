package com.hiberus.cursos.enviadorprofesores.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorDTO {

    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private int movil;
}
