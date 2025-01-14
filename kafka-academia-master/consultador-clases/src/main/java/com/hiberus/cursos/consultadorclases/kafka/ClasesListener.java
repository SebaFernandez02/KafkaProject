package com.hiberus.cursos.consultadorclases.kafka;

import com.hiberus.cursos.consultadorclases.kafka.mapper.ClaseMapper;
import com.hiberus.cursos.consultadorclases.service.ClasesService;
import com.hiberus.cursos.gestorclases.avro.ClaseKey;
import com.hiberus.cursos.gestorclases.avro.ClaseValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Consumer;

@Configuration
@Slf4j
public class ClasesListener {

    @Autowired
    ClasesService clasesService;

    @Bean
    public Consumer<KStream<ClaseKey, ClaseValue>> process() {
        return claseKStream -> claseKStream
                .peek((k, v) -> log.info("Recibida clase con clave: {}", k))
                .peek((k, v) -> {
                    clasesService.guardarClase(new ClaseMapper().toDTO(k,v));
                });
    }
}
