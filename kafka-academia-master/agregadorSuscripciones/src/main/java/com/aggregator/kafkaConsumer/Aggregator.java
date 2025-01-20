package com.aggregator.kafkaConsumer;


import com.suscribers.avro.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
@Slf4j
@Component
public class Aggregator implements org.apache.kafka.streams.kstream.Aggregator<SuscriptionsKey, SuscriptionValue, SuscriptionsValue> {


    @Override
    public SuscriptionsValue apply(SuscriptionsKey suscriptionKey, SuscriptionValue suscriptionValue, SuscriptionsValue suscriptionsValue) {
        log.info("Buscando mensajes repetidos en Aggregator");
        suscriptionsValue = SuscriptionsValue.newBuilder()
                .setSuscriptions(suscriptionsValue.getSuscriptions()
                        .stream()
                        .filter(t -> !suscriptionValue.getName().equals(t.getType()))
                        .collect(Collectors.toList())).build();



        suscriptionsValue.getSuscriptions().add(Suscription.newBuilder().setType(suscriptionValue.getName().name()).build());
        return suscriptionsValue;
    }
}

