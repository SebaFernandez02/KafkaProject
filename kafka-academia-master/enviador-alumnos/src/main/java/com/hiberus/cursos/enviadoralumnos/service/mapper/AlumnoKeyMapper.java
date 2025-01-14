package com.hiberus.cursos.enviadoralumnos.service.mapper;

import com.hiberus.cursos.enviadoralumnos.avro.AlumnoKey;
import com.hiberus.cursos.enviadoralumnos.dto.AlumnoDTO;


public class AlumnoKeyMapper implements Mapper<AlumnoKey, AlumnoDTO>{

    @Override
    public AlumnoKey dtoToEntity(AlumnoDTO dto) {

        return AlumnoKey
                .newBuilder()
                .setIdentificador(dto.getIdentificador())
                .build();
    }
}
