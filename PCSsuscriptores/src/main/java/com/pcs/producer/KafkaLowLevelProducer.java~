package com.pcs.producer;

import com.suscribers.avro.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;


@Configuration
@Setter
@Getter
@Slf4j
public class KafkaLowLevelProducer {

    @Autowired
    private KafkaTemplate<SuscriberFinalKey, SuscriberFinalValue> kafkaTemplate;



    public void send(SuscriberKey suscriberKey, SuscriberValue suscriberValue) {

        SuscriberFinalKey key = SuscriberFinalKey.newBuilder().setId(suscriberKey.getId()).build();

        SuscriberFinalValue value = SuscriberFinalValue.newBuilder()
                .setIdUser(suscriberValue.getIdUser())
                .setUsername(suscriberValue.getUsername())
                .setEmail(suscriberValue.getEmail())
                .setSuscriptions(suscriberValue.getSuscriptions())
                .setDate(LocalDateTime.now().toString())
                .build();

        log.info("Enviando mensaje");
        kafkaTemplate.send("suscribers-final", key, value);
        log.info("Mensaje enviado");
    }
}
