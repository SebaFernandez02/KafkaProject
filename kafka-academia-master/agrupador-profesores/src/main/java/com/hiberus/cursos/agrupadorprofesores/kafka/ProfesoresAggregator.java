package com.hiberus.cursos.agrupadorprofesores.kafka;

import com.hiberus.cursos.agrupadorprofesores.avro.ProfesoresPorAsignaturaYNivelKey;
import com.hiberus.cursos.agrupadorprofesores.avro.ProfesoresPorAsignaturaYNivelValue;
import com.hiberus.cursos.asignadorprofesores.avro.ProfesorAsignadoValue;
import com.hiberus.cursos.enviadorprofesores.avro.ProfesorKey;
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
public class ProfesoresAggregator {

    @Autowired
    Aggregator aggregator;

    @Autowired
    Initializer initializer;

    @Bean
    public Function<KStream<ProfesorKey, ProfesorAsignadoValue>, KStream<ProfesoresPorAsignaturaYNivelKey, ProfesoresPorAsignaturaYNivelValue>> aggregateProfesores() {
        return profesoresStream -> profesoresStream
                .peek((k, v) -> log.info("[aggregateProfesores] Recibido profesor -> clave: {}, valor: {}", k, v))
                .selectKey((k, v) -> ProfesoresPorAsignaturaYNivelKey.newBuilder().setAsignatura(v.getAsignatura()).setNivel(v.getNivel()).build())
                .groupByKey()
                .aggregate(initializer, aggregator, Named.as("PROFESORES_POR_ASIGNATURA_Y_NIVEL"), Materialized.as("PROFESORES_POR_ASIGNATURA_Y_NIVEL"))
                .toStream()
                .peek((k, v) -> log.info("[aggregateProfesores] Profesores agrupados -> clase: {}, valor: {}", k, v));
    }
}