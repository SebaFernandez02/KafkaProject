package com.hiberus.cursos.agrupadorprofesores.kafka;

import com.hiberus.cursos.agrupadorprofesores.avro.Profesor;
import com.hiberus.cursos.agrupadorprofesores.avro.ProfesoresPorAsignaturaYNivelKey;
import com.hiberus.cursos.agrupadorprofesores.avro.ProfesoresPorAsignaturaYNivelValue;
import com.hiberus.cursos.asignadorprofesores.avro.ProfesorAsignadoValue;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Aggregator implements org.apache.kafka.streams.kstream.Aggregator<ProfesoresPorAsignaturaYNivelKey, ProfesorAsignadoValue, ProfesoresPorAsignaturaYNivelValue> {

    @Override
    public ProfesoresPorAsignaturaYNivelValue apply(ProfesoresPorAsignaturaYNivelKey profesoresAgrupadosKey,
                                                    ProfesorAsignadoValue profesorValue,
                                                    ProfesoresPorAsignaturaYNivelValue profesoresAgrupadosValue) {

        profesoresAgrupadosValue = ProfesoresPorAsignaturaYNivelValue.newBuilder()
                .setProfesores(profesoresAgrupadosValue.getProfesores()
                        .stream()
                        .filter(c -> !profesorValue.getDni().equals(c.getDni()))
                        .collect(Collectors.toList())).build();

        profesoresAgrupadosValue.getProfesores().add(createProfesor(profesorValue));

        return profesoresAgrupadosValue;
    }

    private Profesor createProfesor(ProfesorAsignadoValue profesorValue){
        return Profesor.newBuilder()
                .setDni(profesorValue.getDni())
                .setNombre(profesorValue.getNombre())
                .setApellidos(profesorValue.getApellidos())
                .build();
    }
}
