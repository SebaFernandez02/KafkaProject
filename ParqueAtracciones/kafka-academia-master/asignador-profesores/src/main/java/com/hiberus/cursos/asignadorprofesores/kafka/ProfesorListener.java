package com.hiberus.cursos.asignadorprofesores.kafka;

import com.hiberus.cursos.enviadorprofesores.avro.ProfesorKey;
import com.hiberus.cursos.enviadorprofesores.avro.ProfesorValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
public class ProfesorListener {

    @Autowired
    AsignarProfesorService asignarProfesorService;

    @KafkaListener(topics = "profesores")
    public void process(ConsumerRecord<ProfesorKey, ProfesorValue> profesor) {
        log.info("Contratado nuevo profesor. Datos internos: topic -> {}, partition -> {},  offset -> {}, key: {}",
                profesor.topic(), profesor.partition(), profesor.offset(), profesor.key());

        asignarProfesorService.asignar(profesor.key(), profesor.value());

    }
}
