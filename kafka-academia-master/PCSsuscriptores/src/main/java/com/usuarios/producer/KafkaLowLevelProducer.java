package com.usuarios.producer;

import com.usuarios.avro.SuscriberFinalKey;
import com.usuarios.avro.SuscriberFinalValue;
import com.usuarios.avro.SuscriberKey;
import com.usuarios.avro.SuscriberValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;


@Configuration
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

        kafkaTemplate.send("suscribers-final", key, value);
    }
}
