package com.usuarios.kafka;

import com.usuarios.avro.SuscriptionKey;
import com.usuarios.avro.SuscriptionValue;
import com.usuarios.avro.SuscriptionsKey;
import com.usuarios.avro.SuscriptionsValue;
import com.usuarios.kafka.Aggregator;
import com.usuarios.kafka.Initializer;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class AgregadorSuscripciones {

    @Autowired
    Aggregator aggregator;

    @Autowired
    Initializer initializer;


    @Bean
    public Function<KStream<SuscriptionKey, SuscriptionValue>, KStream<SuscriptionsKey, SuscriptionsValue>> aggregateMessages() {
        return suscriptionsStream -> suscriptionsStream
                .peek((k, v) -> log.info("[aggregateMessages] Received message with key: {} and value {}", k, v))
                .selectKey((k, v) -> SuscriptionsKey.newBuilder().setIdUser(k.getIdUser()).build())
                .groupByKey()
                .aggregate(initializer, aggregator, Named.as("suscriptionsAggregated"), Materialized.as("suscriptionsAggregated"))
                .toStream()
                .peek((k, v) -> log.info("[aggregateMessages] Sending message with key: {} and value {}", k, v));
    }

}
