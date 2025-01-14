package com.hiberus.cursos.enviadorprofesores.service.mapper;

import com.hiberus.cursos.enviadorprofesores.avro.ProfesorKey;
import com.hiberus.cursos.enviadorprofesores.dto.ProfesorDTO;

public class ProfesorKeyMapper implements Mapper<ProfesorKey, ProfesorDTO>{

    @Override
    public ProfesorKey dtoToEntity(ProfesorDTO dto) {

        return ProfesorKey
                .newBuilder()
                .setDni(dto.getDni())
                .build();
    }
}
