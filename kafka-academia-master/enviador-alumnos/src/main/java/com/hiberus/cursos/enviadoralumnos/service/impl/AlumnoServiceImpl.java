package com.hiberus.cursos.enviadoralumnos.service.impl;

import com.hiberus.cursos.enviadoralumnos.avro.AlumnoKey;
import com.hiberus.cursos.enviadoralumnos.avro.AlumnoValue;
import com.hiberus.cursos.enviadoralumnos.dto.AlumnoDTO;
import com.hiberus.cursos.enviadoralumnos.service.AlumnoService;
import com.hiberus.cursos.enviadoralumnos.service.mapper.AlumnoKeyMapper;
import com.hiberus.cursos.enviadoralumnos.service.mapper.AlumnoValueMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AlumnoServiceImpl implements AlumnoService {

    @Value("${environment.alumno-topic}")
    private String alumnoTopic;

    @Autowired
    private KafkaTemplate<AlumnoKey, AlumnoValue> kafkaTemplate;

    @Override
    public void crear(AlumnoDTO alumno) {
        if(alumno == null || alumno.getIdentificador() == null) {
            log.error("El alumno es nulo o está incompleto.");
            return;
        }

        AlumnoKey key = new AlumnoKeyMapper().dtoToEntity(alumno);
        AlumnoValue value = new AlumnoValueMapper().dtoToEntity(alumno);

        log.debug("Enviando el alumno al topic de kafka");
        kafkaTemplate.send(alumnoTopic, key, value);
    }

}
