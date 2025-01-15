package com.usuarios;

import com.usuarios.avro.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.BiFunction;


@Configuration
@Slf4j
public class Mixbi {





    @Bean
    public BiFunction< KStream<UserKey, UserValue>, KStream<SuscriptionsKey, SuscriptionsValue>, KStream<SuscriptorKey, SuscriptorValue>> joiner() {
        return (userStream, suscriptionsStream )-> {
            KTable<SuscriptorKey, UserValue> userKTable = userStream
                    // Cambiamos la clave para que sean del mismo tipo y podamos hacer el join
                    .selectKey((k, v) -> SuscriptorKey.newBuilder().setId(k.getId()).build())
                    .toTable(Named.as("suscriptors_users"), Materialized.as("suscriptors_users"));

            KTable<SuscriptorKey, SuscriptionsValue> suscriptionsKTable = suscriptionsStream
                    // Cambiamos la clave para que sean del mismo tipo y podamos hacer el join
                    .selectKey((k, v) -> SuscriptorKey.newBuilder().setId(k.getIdUser()).build())
                    .toTable(Named.as("suscriptors_suscriptions"), Materialized.as("suscriptors_suscriptions"));

            return userKTable.join(suscriptionsKTable,    // Las dos tablas que hacen el join

                            // La función de join (se puede llevar a una clase que implemente la interfaz ValueJoiner https://kafka.apache.org/32/javadoc/org/apache/kafka/streams/kstream/ValueJoiner.html)
                            (userValue, suscriptionsValue) -> SuscriptorValue.newBuilder()
                                    .setIdUser(userValue.getId())
                                    .setUsername(userValue.getUsername())
                                    .setEmail(userValue.getEmail())
                                    .setSuscriptions(suscriptionsValue.getSuscriptions())
                                    .build())

                    // Como el resultado de un join de dos KTables es una KTable, lo volvemos a pasar a KStream
                    .toStream()
                    .peek((k, v) -> log.info("[joiner] Sending message with key: {} and value: {}", k, v));
        };
    }
}
