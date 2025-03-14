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
    private KafkaTemplate<SuscriptionFinalKey, SuscriptionFinalValue> kafkaTemplate;



    public void send(SuscriptionKey suscriptionKey, SuscriptionValue suscriptionValue) {

        SuscriptionFinalKey key = SuscriptionFinalKey.newBuilder().setIdUser(suscriptionKey.getIdUser()).build();

        SuscriptionFinalValue value = SuscriptionFinalValue.newBuilder()
                        .setIdUser(suscriptionValue.getIdUser())
                                .setName(suscriptionValue.getName())
                                        .setDate(LocalDateTime.now().toString()).build();

        log.info("Enviando mensaje");
        kafkaTemplate.send("suscriptions-date", key, value);
        log.info("Mensaje enviado");
    }
}
