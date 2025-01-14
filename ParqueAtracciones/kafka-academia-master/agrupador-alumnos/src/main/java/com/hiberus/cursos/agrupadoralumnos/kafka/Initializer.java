package com.hiberus.cursos.agrupadoralumnos.kafka;

import com.hiberus.cursos.agrupadoralumnos.avro.AlumnosPorAsignaturaYNivelValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Initializer implements org.apache.kafka.streams.kstream.Initializer<AlumnosPorAsignaturaYNivelValue> {
    @Override
    public AlumnosPorAsignaturaYNivelValue apply() {
        return AlumnosPorAsignaturaYNivelValue.newBuilder()
                .setAlumnos(new ArrayList<>())
                .build();
    }
}
