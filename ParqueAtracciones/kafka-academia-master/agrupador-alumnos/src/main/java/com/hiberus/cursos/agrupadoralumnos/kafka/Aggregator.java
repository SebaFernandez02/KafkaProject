package com.hiberus.cursos.agrupadoralumnos.kafka;

import com.hiberus.cursos.agrupadoralumnos.avro.Alumno;
import com.hiberus.cursos.agrupadoralumnos.avro.AlumnosPorAsignaturaYNivelKey;
import com.hiberus.cursos.agrupadoralumnos.avro.AlumnosPorAsignaturaYNivelValue;
import com.hiberus.cursos.enviadoralumnos.avro.AlumnoValue;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Aggregator implements org.apache.kafka.streams.kstream.Aggregator<AlumnosPorAsignaturaYNivelKey, AlumnoValue, AlumnosPorAsignaturaYNivelValue> {

    @Override
    public AlumnosPorAsignaturaYNivelValue apply(AlumnosPorAsignaturaYNivelKey alumnosAgrupadosKey,
                                                    AlumnoValue alumnoValue,
                                                    AlumnosPorAsignaturaYNivelValue alumnosAgrupadosValue) {

        alumnosAgrupadosValue = AlumnosPorAsignaturaYNivelValue.newBuilder()
                .setAlumnos(alumnosAgrupadosValue.getAlumnos()
                        .stream()
                        .filter(c -> !alumnoValue.getIdentificador().equals(c.getIdentificador()))
                        .collect(Collectors.toList())).build();

        alumnosAgrupadosValue.getAlumnos().add(createAlumno(alumnoValue));

        return alumnosAgrupadosValue;
    }

    private Alumno createAlumno(AlumnoValue alumnoValue){
        return Alumno.newBuilder()
                .setIdentificador(alumnoValue.getIdentificador())
                .setNombre(alumnoValue.getNombre())
                .setApellidos(alumnoValue.getApellidos())
                .build();
    }
}
