package com.usuarios.service.impl;

import com.usuarios.avro.SuscriptionKey;
import com.usuarios.avro.SuscriptionValue;
import com.usuarios.dto.SuscriptionDto;
import com.usuarios.dto.SuscriptionType;
import com.usuarios.service.SuscriptionService;
import com.usuarios.service.mapper.SuscriptionKeyMapper;
import com.usuarios.service.mapper.SuscriptionValueMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class SuscriptionServiceImpl implements SuscriptionService {

    private String suscriptionsTopic = "suscriptions";

    @Autowired
    private KafkaTemplate<SuscriptionKey, SuscriptionValue> kafkaTemplate;


    @Override
    public void createSuscription(SuscriptionDto suscriptionDto) {
        if (suscriptionDto == null || suscriptionDto.getIdUser().isBlank() || suscriptionDto.getType().isBlank()){
            log.error("La suscripcion es nula o esta incompleta");
            return;
        }

        try {
            String type = SuscriptionType.parse(suscriptionDto.getType()).toString();
            suscriptionDto.setType(type);
        }catch (Exception e){
            log.error("Tipo de suscripcion invalida");
        }

        SuscriptionKey suscriptionKey = new SuscriptionKeyMapper().dtoToEntity(suscriptionDto);
        SuscriptionValue suscriptionValue = new SuscriptionValueMapper().dtoToEntity(suscriptionDto);

        log.debug("Enviando la suscripcion al topic de Kafka");
        kafkaTemplate.send(suscriptionsTopic, suscriptionKey, suscriptionValue);
    }
}
