package com.hiberus.cursos.consultadorclases.kafka.mapper;


import com.hiberus.cursos.consultadorclases.dto.AlumnoDTO;
import com.hiberus.cursos.consultadorclases.dto.ClaseDTO;
import com.hiberus.cursos.consultadorclases.dto.ProfesorDTO;
import com.hiberus.cursos.gestorclases.avro.ClaseKey;
import com.hiberus.cursos.gestorclases.avro.ClaseValue;

import java.util.List;
import java.util.stream.Collectors;

public class ClaseMapper implements Mapper<ClaseKey, ClaseValue, ClaseDTO> {


    @Override
    public ClaseDTO toDTO(ClaseKey key, ClaseValue value) {

        List<ProfesorDTO> profesores = value.getProfesores()
                .stream()
                .map(p -> ProfesorDTO.builder().dni(p.getDni()).apellidos(p.getApellidos()).nombre(p.getNombre()).build())
                .collect(Collectors.toList());

        List<AlumnoDTO> alumnos = value.getAlumnos()
                .stream()
                .map(u -> AlumnoDTO.builder().identificador(u.getIdentificador()).apellidos(u.getApellidos()).nombre(u.getNombre()).build())
                .collect(Collectors.toList());

        return ClaseDTO.builder()
                .nivel(key.getNivel().toString())
                .asignatura(key.getAsignatura().toString())
                .alumnos(alumnos)
                .profesores(profesores)
                .build();

    }
}
