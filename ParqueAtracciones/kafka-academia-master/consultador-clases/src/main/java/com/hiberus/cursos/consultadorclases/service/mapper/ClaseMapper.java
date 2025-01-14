package com.hiberus.cursos.consultadorclases.service.mapper;


import com.hiberus.cursos.consultadorclases.dto.AlumnoDTO;
import com.hiberus.cursos.consultadorclases.dto.ClaseDTO;
import com.hiberus.cursos.consultadorclases.dto.ProfesorDTO;
import com.hiberus.cursos.consultadorclases.model.Alumno;
import com.hiberus.cursos.consultadorclases.model.Clase;
import com.hiberus.cursos.consultadorclases.model.Profesor;

import java.util.List;

public class ClaseMapper extends GenericMapper<Clase, ClaseDTO>{

    @Override
    public ClaseDTO entityToDto(Clase clase) {

        List<AlumnoDTO> alumnos = new AlumnoMapper().entityListToDtoList(clase.getAlumnos());
        List<ProfesorDTO> profesores = new ProfesorMapper().entityListToDtoList(clase.getProfesores());

       return ClaseDTO.builder()
               .asignatura(clase.getAsignatura())
               .alumnos(alumnos)
               .profesores(profesores)
               .nivel(clase.getNivel())
               .build();
    }

    @Override
    public Clase dtoToEntity(ClaseDTO dto) {

        List<Alumno> alumnos = new AlumnoMapper().dtoListToEntityList(dto.getAlumnos());
        List<Profesor> profesores = new ProfesorMapper().dtoListToEntityList(dto.getProfesores());

        Clase clase = new Clase();
        clase.setAlumnos(alumnos);
        clase.setAsignatura(dto.getAsignatura());
        clase.setNivel(dto.getNivel());
        clase.setProfesores(profesores);
        return clase;
    }
}
