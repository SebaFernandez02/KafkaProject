package com.hiberus.cursos.enviadorprofesores.service.mapper;

import com.hiberus.cursos.enviadorprofesores.avro.ProfesorValue;
import com.hiberus.cursos.enviadorprofesores.dto.ProfesorDTO;

public class ProfesorValueMapper implements Mapper<ProfesorValue, ProfesorDTO> {

    @Override
    public ProfesorValue dtoToEntity(ProfesorDTO dto) {

        return ProfesorValue.newBuilder()
                .setDni(dto.getDni())
                .setNombre(dto.getNombre())
                .setApellidos(dto.getApellidos())
                .setDireccion(dto.getDireccion())
                .setMovil(dto.getMovil())
                .build();
    }
}
