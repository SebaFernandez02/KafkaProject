package com.aggregator.kafkaConsumer;

import com.suscribers.avro.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Aggregator implements org.apache.kafka.streams.kstream.Aggregator<SuscriptionsKey, SuscriptionFinalValue, SuscriptionsValue> {


    @Override
    public SuscriptionsValue apply(SuscriptionsKey suscriptionKey, SuscriptionFinalValue suscriptionValue, SuscriptionsValue suscriptionsValue) {
        log.info("Buscando mensajes repetidos en Aggregator");
        suscriptionsValue = SuscriptionsValue.newBuilder()
                .setSuscriptions(suscriptionsValue.getSuscriptions()
                        .stream()
                        .filter(t -> !suscriptionValue.getName().toString().equals(t.getType()) && (LocalDateTime.parse(suscriptionValue.getDate()).getDayOfYear()) == (LocalDateTime.parse(t.getDate()).getDayOfYear()))                        .collect(Collectors.toList())).build();



        suscriptionsValue.getSuscriptions().add(Suscription.newBuilder().setType(suscriptionValue.getName().name()).setDate(suscriptionValue.getDate()).build());
        return suscriptionsValue;
    }
}

