package com.pcs;

import com.pcs.producer.KafkaLowLevelProducer;
import com.suscribers.avro.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class KafkaLowLevelProducerTest {

 /*   private KafkaLowLevelProducer kafkaLowLevelProducer;

    @Mock
    private KafkaTemplate<SuscriberFinalKey, SuscriberFinalValue> kafkaTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        kafkaLowLevelProducer = new KafkaLowLevelProducer();
        kafkaLowLevelProducer.setKafkaTemplate(kafkaTemplate);
    }

    @Test
    void testSendEnviaClaveCorrecta() {
        // Arrange
        SuscriberKey suscriberKey = new SuscriberKey("123");
        SuscriberValue suscriberValue = new SuscriberValue("456", "username", "email@example.com", List.of(Suscription.newBuilder().setType("ACUATICO").build()));

        // Act
        kafkaLowLevelProducer.send(suscriberKey, suscriberValue);

        // Assert
        ArgumentCaptor<SuscriberFinalKey> keyCaptor = ArgumentCaptor.forClass(SuscriberFinalKey.class);
        verify(kafkaTemplate).send(eq("suscribers-final"), keyCaptor.capture(), any(SuscriberFinalValue.class));
        assertEquals("123", keyCaptor.getValue().getId());
    }

    @Test
    void testSendEnviaValorCorrecto() {
        // Arrange
        SuscriberKey suscriberKey = new SuscriberKey("123");
        SuscriberValue suscriberValue = new SuscriberValue("456", "username", "email@example.com", List.of(Suscription.newBuilder().setType("ACUATICO").build()));

        // Act
        kafkaLowLevelProducer.send(suscriberKey, suscriberValue);

        // Assert
        ArgumentCaptor<SuscriberFinalValue> valueCaptor = ArgumentCaptor.forClass(SuscriberFinalValue.class);
        verify(kafkaTemplate).send(eq("suscribers-final"), any(SuscriberFinalKey.class), valueCaptor.capture());
        assertEquals("456", valueCaptor.getValue().getIdUser());
    }

    @Test
    void testSendEnviaFechaCorrecta() {
        // Arrange
        SuscriberKey suscriberKey = new SuscriberKey("123");
        SuscriberValue suscriberValue = new SuscriberValue("456", "username", "email@example.com", List.of(Suscription.newBuilder().setType("ACUATICO").build()));

        // Act
        kafkaLowLevelProducer.send(suscriberKey, suscriberValue);

        // Assert
        ArgumentCaptor<SuscriberFinalValue> valueCaptor = ArgumentCaptor.forClass(SuscriberFinalValue.class);
        verify(kafkaTemplate).send(eq("suscribers-final"), any(SuscriberFinalKey.class), valueCaptor.capture());
        LocalDateTime dateTime = LocalDateTime.parse(valueCaptor.getValue().getDate());
        assertEquals(LocalDateTime.now().getYear(), dateTime.getYear());
    }

    @Test
    void testSendLlamaMetodoSendUnaVez() {
        // Arrange
        SuscriberKey suscriberKey = new SuscriberKey("123");
        SuscriberValue suscriberValue = new SuscriberValue("456", "username", "email@example.com", List.of(Suscription.newBuilder().setType("ACUATICO").build()));

        // Act
        kafkaLowLevelProducer.send(suscriberKey, suscriberValue);

        // Assert
        verify(kafkaTemplate, times(1)).send(eq("suscribers-final"), any(SuscriberFinalKey.class), any(SuscriberFinalValue.class));
    }*/
}
