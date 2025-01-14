package com.hiberus.cursos.consultadorclases.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlumnoDTO {

    private String identificador;
    private String nombre;
    private String apellidos;

}
