package com.hiberus.cursos.consultadorclases.service.mapper;

import com.hiberus.cursos.consultadorclases.dto.ProfesorDTO;
import com.hiberus.cursos.consultadorclases.model.Profesor;


public class ProfesorMapper extends GenericMapper<Profesor, ProfesorDTO> {

    @Override
    public ProfesorDTO entityToDto(Profesor entity) {
        return ProfesorDTO.builder()
                .dni(entity.getDni())
                .apellidos(entity.getApellidos())
                .nombre(entity.getNombre())
                .build();
    }

    @Override
    public Profesor dtoToEntity(ProfesorDTO dto) {
        Profesor profesor = new Profesor();
        profesor.setApellidos(dto.getApellidos());
        profesor.setNombre(dto.getNombre());
        profesor.setDni(dto.getDni());
        return profesor;
    }

}
