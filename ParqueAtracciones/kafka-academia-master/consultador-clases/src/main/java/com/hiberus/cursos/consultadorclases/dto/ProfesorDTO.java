package com.hiberus.cursos.consultadorclases.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfesorDTO {

    private String dni;
    private String nombre;
    private String apellidos;
}
