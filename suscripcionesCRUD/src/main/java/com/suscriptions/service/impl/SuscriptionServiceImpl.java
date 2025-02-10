package com.suscriptions.service.impl;

import com.suscribers.avro.SuscriptionKey;
import com.suscribers.avro.SuscriptionValue;
import com.suscriptions.dto.SuscriptionDto;
import com.suscriptions.dto.SuscriptionType;
import com.suscriptions.service.SuscriptionService;
import com.suscriptions.service.mapper.SuscriptionKeyMapper;
import com.suscriptions.service.mapper.SuscriptionValueMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.security.InvalidParameterException;

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
            throw new InvalidParameterException("La suscripcion es nula o incompleta");
        }

        try {
            String type = SuscriptionType.parse(suscriptionDto.getType()).toString();
            suscriptionDto.setType(type);

            SuscriptionKey suscriptionKey = new SuscriptionKeyMapper().dtoToEntity(suscriptionDto);
            SuscriptionValue suscriptionValue = new SuscriptionValueMapper().dtoToEntity(suscriptionDto);

            log.debug("Enviando la suscripcion al topic de Kafka");
            kafkaTemplate.send(suscriptionsTopic, suscriptionKey, suscriptionValue);
        }catch (Exception e){
            log.error("Tipo de suscripcion invalida");
            throw new InvalidParameterException("Tipo de suscripcion invalida");
        }

    }
}
