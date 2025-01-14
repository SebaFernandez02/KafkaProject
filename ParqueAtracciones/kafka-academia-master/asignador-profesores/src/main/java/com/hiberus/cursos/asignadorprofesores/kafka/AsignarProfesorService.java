package com.hiberus.cursos.asignadorprofesores.kafka;

import com.hiberus.cursos.asignadorprofesores.avro.*;
import com.hiberus.cursos.enviadorprofesores.avro.ProfesorKey;
import com.hiberus.cursos.enviadorprofesores.avro.ProfesorValue;
import com.hiberus.cursos.gestorclases.avro.Asignatura;
import com.hiberus.cursos.gestorclases.avro.Nivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AsignarProfesorService {

    @Value("${environment.profesores-asignatura-topic}")
    private String profesoresAsignaturaTopic;

    @Autowired
    private KafkaTemplate<ProfesorKey, ProfesorAsignadoValue> kafkaTemplate;

    private static final SecureRandom random = new SecureRandom();

    public void asignar(ProfesorKey key, ProfesorValue value){
        ProfesorAsignadoValue asignado = ProfesorAsignadoValue.newBuilder()
                .setDni(value.getDni())
                .setNombre(value.getNombre())
                .setApellidos(value.getApellidos())
                .setDireccion(value.getApellidos())
                .setMovil(value.getMovil())
                .setAsignatura(randomEnum(Asignatura.class))
                .setNivel(randomEnum(Nivel.class))
                .build();

        kafkaTemplate.send(profesoresAsignaturaTopic, key, asignado);
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
