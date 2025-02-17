package com.aggregator.kafkaConsumer;

import com.suscribers.avro.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Function;

@Configuration
@Slf4j
public class AgregadorSuscripciones {

    @Autowired
    Aggregator aggregator;

    @Autowired
    Initializer initializer;


    @Bean
    public Function<KStream<SuscriptionFinalKey, SuscriptionFinalValue>, KStream<SuscriptionsKey, SuscriptionsValue>> aggregateMessages() {
        return suscriptionsStream -> suscriptionsStream
                .peek((k, v) -> log.info("[aggregateMessages] Received message with key: {} and value {}", k, v))
                .selectKey((k, v) -> SuscriptionsKey.newBuilder().setIdUser(k.getIdUser()).build())
                .groupByKey()
                .aggregate(initializer, aggregator, Named.as("suscriptionsAggregated"), Materialized.as("suscriptionsAggregated"))
                .toStream()
                .peek((k, v) -> log.info("[aggregateMessages] Sending message with key: {} and value {}", k, v));
    }

}
