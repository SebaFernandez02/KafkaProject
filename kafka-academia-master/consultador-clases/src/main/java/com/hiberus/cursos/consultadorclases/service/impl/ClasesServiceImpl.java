package com.hiberus.cursos.consultadorclases.service.impl;

import com.hiberus.cursos.consultadorclases.dto.ClaseDTO;
import com.hiberus.cursos.consultadorclases.model.Alumno;
import com.hiberus.cursos.consultadorclases.model.Clase;
import com.hiberus.cursos.consultadorclases.model.Profesor;
import com.hiberus.cursos.consultadorclases.repository.ClasesRepository;
import com.hiberus.cursos.consultadorclases.service.ClasesService;
import com.hiberus.cursos.consultadorclases.service.mapper.AlumnoMapper;
import com.hiberus.cursos.consultadorclases.service.mapper.ClaseMapper;
import com.hiberus.cursos.consultadorclases.service.mapper.ProfesorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClasesServiceImpl implements ClasesService {

    @Autowired
    ClasesRepository clasesRepository;

    @Override
    public List<ClaseDTO> getClases() {
        List<Clase> clases = clasesRepository.findAll();
        return new ClaseMapper().entityListToDtoList(clases);
    }

    @Override
    public void guardarClase(ClaseDTO clase) {
        Clase claseExistente = clasesRepository.findByAsignaturaAndNivel(clase.getAsignatura(), clase.getNivel());

        if(claseExistente != null){
            List<Alumno> alumnos = new AlumnoMapper().dtoListToEntityList(clase.getAlumnos());
            List<Profesor> profesores = new ProfesorMapper().dtoListToEntityList(clase.getProfesores());
            claseExistente.setProfesores(profesores);
            claseExistente.setAlumnos(alumnos);
            clasesRepository.save(claseExistente);
        }else {
            Clase entity = new ClaseMapper().dtoToEntity(clase);
            clasesRepository.save(entity);
        }
    }
}
