package com.aggregator;

import com.aggregator.avro.SuscriptionValue;
import com.aggregator.avro.Suscription;
import com.aggregator.avro.SuscriptionsKey;
import com.aggregator.avro.SuscriptionsValue;
import com.aggregator.kafkaConsumer.Aggregator;
import es.curso.kafka.avro.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AggregatorTest {

    @InjectMocks
    private Aggregator aggregator;

    private String idUser = "122345";

    @Test
    void testApply() {
        // Datos de prueba
        SuscriptionsValue currentSuscriptions = SuscriptionsValue.newBuilder()
                .setSuscriptions(new ArrayList<>(List.of(
                        Suscription.newBuilder().setType("PREMIUM").build()
                )))
                .build();

        SuscriptionValue newSuscription = SuscriptionValue.newBuilder().setIdUser(idUser)
                .setName(Tipo.ACUATICO)
                .build();

        SuscriptionsKey key = SuscriptionsKey.newBuilder().setIdUser("user-1").build();

        // Ejecutar
        SuscriptionsValue result = aggregator.apply(key, newSuscription, currentSuscriptions);

        // Verificar
        Assertions.assertEquals(2, result.getSuscriptions().size());
    }
}
