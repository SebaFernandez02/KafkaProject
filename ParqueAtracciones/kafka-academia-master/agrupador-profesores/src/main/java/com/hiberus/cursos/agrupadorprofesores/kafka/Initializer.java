package com.hiberus.cursos.agrupadorprofesores.kafka;

import com.hiberus.cursos.agrupadorprofesores.avro.ProfesoresPorAsignaturaYNivelValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Initializer implements org.apache.kafka.streams.kstream.Initializer<ProfesoresPorAsignaturaYNivelValue> {
    @Override
    public ProfesoresPorAsignaturaYNivelValue apply() {
        return ProfesoresPorAsignaturaYNivelValue.newBuilder()
                .setProfesores(new ArrayList<>())
                .build();
    }
}
