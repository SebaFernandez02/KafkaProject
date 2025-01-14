package com.hiberus.cursos.agrupadoralumnos.kafka;

import com.hiberus.cursos.agrupadoralumnos.avro.AlumnosPorAsignaturaYNivelKey;
import com.hiberus.cursos.agrupadoralumnos.avro.AlumnosPorAsignaturaYNivelValue;
import com.hiberus.cursos.enviadoralumnos.avro.AlumnoKey;
import com.hiberus.cursos.enviadoralumnos.avro.AlumnoValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@Slf4j
public class AlumnosAggregator {

    @Autowired
    Aggregator aggregator;

    @Autowired
    Initializer initializer;

    @Bean
    public Function<KStream<AlumnoKey, AlumnoValue>, KStream<AlumnosPorAsignaturaYNivelKey, AlumnosPorAsignaturaYNivelValue>> aggregateAlumnos() {
        return profesoresStream -> profesoresStream
                .peek((k, v) -> log.info("[aggregateAlumnos] Recibido alumno -> clave: {}, valor: {}", k, v))
                .selectKey((k, v) -> AlumnosPorAsignaturaYNivelKey.newBuilder().setAsignatura(v.getAsignatura()).setNivel(v.getNivel()).build())
                .groupByKey()
                .aggregate(initializer, aggregator, Named.as("ALUMNOS_POR_ASIGNATURA_Y_NIVEL"), Materialized.as("ALUMNOS_POR_ASIGNATURA_Y_NIVEL"))
                .toStream()
                .peek((k, v) -> log.info("[aggregateAlumnos] Alumnos agrupados -> clase: {}, valor: {}", k, v));
    }
}