package com.hiberus.cursos.consultadorclases.service.mapper;

import com.hiberus.cursos.consultadorclases.dto.AlumnoDTO;
import com.hiberus.cursos.consultadorclases.model.Alumno;


public class AlumnoMapper extends GenericMapper<Alumno, AlumnoDTO> {

    @Override
    public AlumnoDTO entityToDto(Alumno entity) {
        return AlumnoDTO.builder()
                .identificador(entity.getIdentificador())
                .nombre(entity.getNombre())
                .apellidos(entity.getApellidos())
                .build();
    }

    @Override
    public Alumno dtoToEntity(AlumnoDTO dto) {
        Alumno alumno = new Alumno();
        alumno.setApellidos(dto.getApellidos());
        alumno.setNombre(dto.getNombre());
        alumno.setIdentificador(dto.getIdentificador());
        return alumno;
    }

}
