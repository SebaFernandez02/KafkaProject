package com.hiberus.cursos.enviadoralumnos.service.mapper;


import com.hiberus.cursos.enviadoralumnos.avro.AlumnoValue;
import com.hiberus.cursos.enviadoralumnos.dto.AlumnoDTO;
import com.hiberus.cursos.gestorclases.avro.Asignatura;
import com.hiberus.cursos.gestorclases.avro.Nivel;


public class AlumnoValueMapper implements Mapper<AlumnoValue, AlumnoDTO> {

    @Override
    public AlumnoValue dtoToEntity(AlumnoDTO dto) {

        return AlumnoValue.newBuilder()
                .setIdentificador(dto.getIdentificador())
                .setNombre(dto.getNombre())
                .setApellidos(dto.getApellidos())
                .setDireccion(dto.getDireccion())
                .setDniTutor(dto.getDniTutor())
                .setMovilTutor(dto.getMovilTutor())
                .setNivel(Nivel.valueOf(dto.getNivel()))
                .setAsignatura(Asignatura.valueOf(dto.getAsignatura()))
                .build();
    }
}
