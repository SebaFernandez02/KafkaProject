package com.pcs;

import com.pcs.avro.SuscriberFinalKey;
import com.pcs.avro.SuscriberFinalValue;
import com.pcs.avro.SuscriberKey;
import com.pcs.avro.SuscriberValue;
import com.pcs.consumer.KafkaLowLevelConsumer;
import com.pcs.producer.KafkaLowLevelProducer;
import com.usuarios.avro.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@EmbeddedKafka(partitions = 1, topics = {"suscribers", "suscribers-final"})
@SpringBootTest
class KafkaLowLevelConsumerTest {

    @Autowired
    private KafkaLowLevelConsumer kafkaLowLevelConsumer;

    @MockBean
    private KafkaLowLevelProducer kafkaLowLevelProducer;

    @MockBean
    private KafkaTemplate<SuscriberFinalKey, SuscriberFinalValue> kafkaTemplate;

    @Test
    void testConsumerProcessesMessageAndCallsProducer() {
        // Arrange
        SuscriberKey suscriberKey = SuscriberKey.newBuilder().setId("12345").build();
        SuscriberValue suscriberValue = SuscriberValue.newBuilder()
                .setIdUser("12345")
                .setUsername("test_user")
                .setEmail("test_user@example.com")
                .setSuscriptions(new ArrayList<>()) // Empty subscriptions
                .build();

        ConsumerRecord<SuscriberKey, SuscriberValue> record = new ConsumerRecord<>(
                "suscribers",
                0,
                0,
                suscriberKey,
                suscriberValue
        );

        // Act
        kafkaLowLevelConsumer.process(record);

        // Assert
        ArgumentCaptor<SuscriberKey> keyCaptor = ArgumentCaptor.forClass(SuscriberKey.class);
        ArgumentCaptor<SuscriberValue> valueCaptor = ArgumentCaptor.forClass(SuscriberValue.class);

        verify(kafkaLowLevelProducer, times(1)).send(keyCaptor.capture(), valueCaptor.capture());

        assertEquals(suscriberKey, keyCaptor.getValue());
        assertEquals(suscriberValue, valueCaptor.getValue());
    }

    @Test
    void testProducerSendsMessageToKafka() {
        // Arrange
        KafkaTemplate<SuscriberFinalKey, SuscriberFinalValue> mockedKafkaTemplate = mock(KafkaTemplate.class);
        KafkaLowLevelProducer producer = new KafkaLowLevelProducer();
        producer.setKafkaTemplate(mockedKafkaTemplate); // Inyectamos el mock manualmente

        SuscriberKey suscriberKey = SuscriberKey.newBuilder().setId("12345").build();
        SuscriberValue suscriberValue = SuscriberValue.newBuilder()
                .setIdUser("12345")
                .setUsername("test_user")
                .setEmail("test_user@example.com")
                .setSuscriptions(new ArrayList<>()) // Empty subscriptions
                .build();

        SuscriberFinalKey expectedKey = SuscriberFinalKey.newBuilder().setId("12345").build();
        SuscriberFinalValue expectedValue = SuscriberFinalValue.newBuilder()
                .setIdUser("12345")
                .setUsername("test_user")
                .setEmail("test_user@example.com")
                .setSuscriptions(new ArrayList<>())
                .setDate("2025-01-20T12:00:00") // Fecha simulada
                .build();

        // Act
        producer.send(suscriberKey, suscriberValue);

        // Assert
        ArgumentCaptor<SuscriberFinalKey> keyCaptor = ArgumentCaptor.forClass(SuscriberFinalKey.class);
        ArgumentCaptor<SuscriberFinalValue> valueCaptor = ArgumentCaptor.forClass(SuscriberFinalValue.class);

        verify(mockedKafkaTemplate, times(1)).send(eq("suscribers-final"), keyCaptor.capture(), valueCaptor.capture());

        assertEquals(expectedKey, keyCaptor.getValue());
        assertNotNull(valueCaptor.getValue().getDate()); // Comprobamos que se genera una fecha
    }

}
