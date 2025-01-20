package com.aggregator.kafkaConsumer;

import com.aggregator.avro.*;
import com.aggregator.avro.SuscriptionValue;
import com.aggregator.avro.SuscriptionsKey;
import com.aggregator.avro.SuscriptionsValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.aggregator.avro.Suscription;
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

