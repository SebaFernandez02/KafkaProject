package com.aggregator.kafkaConsumer;

import com.suscribers.avro.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Slf4j
@Component
public class Initializer implements org.apache.kafka.streams.kstream.Initializer<SuscriptionsValue> {
    @Override
    public SuscriptionsValue apply() {
        log.info("Creando lista de suscripciones");
        return SuscriptionsValue.newBuilder()
                .setSuscriptions(new ArrayList<>())
                .build();
    }
}
