package com.hiberus.cursos.enviadorprofesores.service.impl;

import com.hiberus.cursos.enviadorprofesores.avro.ProfesorKey;
import com.hiberus.cursos.enviadorprofesores.avro.ProfesorValue;
import com.hiberus.cursos.enviadorprofesores.dto.ProfesorDTO;
import com.hiberus.cursos.enviadorprofesores.service.ProfesorService;
import com.hiberus.cursos.enviadorprofesores.service.mapper.ProfesorKeyMapper;
import com.hiberus.cursos.enviadorprofesores.service.mapper.ProfesorValueMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProfesorServiceImpl implements ProfesorService {

    @Value("${environment.profesor-topic}")
    private String profesorTopic;

    @Autowired
    private KafkaTemplate<ProfesorKey, ProfesorValue> kafkaTemplate;

    @Override
    public void crear(ProfesorDTO profesor) {
        if(profesor == null || profesor.getDni() == null) {
            log.error("El profesor es nulo o está incompleto.");
            return;
        }

        ProfesorKey key = new ProfesorKeyMapper().dtoToEntity(profesor);
        ProfesorValue value = new ProfesorValueMapper().dtoToEntity(profesor);

        log.debug("Enviando el profesor al topic de kafka");
        kafkaTemplate.send(profesorTopic, key, value);
    }

}
