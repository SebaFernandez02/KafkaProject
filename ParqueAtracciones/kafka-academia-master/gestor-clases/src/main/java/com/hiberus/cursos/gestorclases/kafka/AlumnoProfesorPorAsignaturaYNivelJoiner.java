package com.hiberus.cursos.gestorclases.kafka;

import com.hiberus.cursos.agrupadoralumnos.avro.AlumnosPorAsignaturaYNivelKey;
import com.hiberus.cursos.agrupadoralumnos.avro.AlumnosPorAsignaturaYNivelValue;
import com.hiberus.cursos.agrupadorprofesores.avro.ProfesoresPorAsignaturaYNivelKey;
import com.hiberus.cursos.agrupadorprofesores.avro.ProfesoresPorAsignaturaYNivelValue;
import com.hiberus.cursos.gestorclases.avro.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.BiFunction;

@Configuration
@Slf4j
public class AlumnoProfesorPorAsignaturaYNivelJoiner {

    @Bean
    public BiFunction<KStream<ProfesoresPorAsignaturaYNivelKey, ProfesoresPorAsignaturaYNivelValue>,
            KStream<AlumnosPorAsignaturaYNivelKey, AlumnosPorAsignaturaYNivelValue>, KStream<ClaseKey, ClaseValue>> joiner() {
        return (profesoresStream, alumnosStream) -> {

            KTable<ClaseKey, ProfesoresPorAsignaturaYNivelValue> profesoresKTable = profesoresStream
                    .selectKey((k, v) -> ClaseKey.newBuilder().setAsignatura(k.getAsignatura()).setNivel(k.getNivel()).build())
                    .toTable(Named.as("CLASE_PROFESORES"), Materialized.as("CLASE_PROFESORES"));

            KTable<ClaseKey, AlumnosPorAsignaturaYNivelValue> alumnosKTable = alumnosStream
                    .selectKey((k, v) -> ClaseKey.newBuilder().setAsignatura(k.getAsignatura()).setNivel(k.getNivel()).build())
                    .toTable(Named.as("CLASE_ALUMNOS"), Materialized.as("CLASE_ALUMNOS"));

            return profesoresKTable.join(alumnosKTable, (profesoresValue, alumnosValue) -> ClaseValue.newBuilder()
                                    .setAlumnos(alumnosValue.getAlumnos())
                                    .setProfesores(profesoresValue.getProfesores())
                                    .build())

                    .toStream()
                    .peek((k, v) -> log.info("Creadas clase -> clave: {} valor: {}", k, v));
        };
    }
}
