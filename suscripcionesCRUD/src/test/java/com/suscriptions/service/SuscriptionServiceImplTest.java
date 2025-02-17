package com.suscriptions.service;

import com.suscribers.avro.SuscriptionKey;
import com.suscribers.avro.SuscriptionValue;
import com.suscriptions.dto.SuscriptionDto;
import com.suscriptions.service.impl.SuscriptionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.kafka.core.KafkaTemplate;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SuscriptionServiceImplTest {

    @InjectMocks
    private SuscriptionServiceImpl suscriptionService;

    @Mock
    private KafkaTemplate<SuscriptionKey, SuscriptionValue> kafkaTemplate;

    @Captor
    private ArgumentCaptor<SuscriptionKey> keyCaptor;

    @Captor
    private ArgumentCaptor<SuscriptionValue> valueCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSuscriptionValidInputShouldSendToKafka() {
        // Arrange
        SuscriptionDto validDto = new SuscriptionDto();
        validDto.setIdUser("123e4567-e89b-12d3-a456-426614174000");
        validDto.setType("ACUATICO");

        // Act
        suscriptionService.createSuscription(validDto);

        // Assert
        verify(kafkaTemplate, times(1)).send(eq("suscriptions"), keyCaptor.capture(), valueCaptor.capture());

        SuscriptionKey capturedKey = keyCaptor.getValue();
        SuscriptionValue capturedValue = valueCaptor.getValue();


        assertEquals(validDto.getIdUser(), capturedKey.getIdUser(), "El ID del usuario debería coincidir.");
        assertEquals("ACUATICO", capturedValue.getName().name(), "El tipo de suscripción debería ser 'PREMIUM'.");
    }

    @Test
    void createSuscriptionNullInputShouldThrowException() {
        // Arrange
        SuscriptionDto nullDto = null;

        // Act & Assert
        assertThrows(InvalidParameterException.class,
                () -> suscriptionService.createSuscription(nullDto),
                "Debería lanzarse una excepción para un DTO nulo.");

        verifyNoInteractions(kafkaTemplate);
    }


    @Test
    void createSuscriptionIncompleteInputShouldThrowException() {
        // Arrange
        SuscriptionDto incompleteDto = new SuscriptionDto();
        incompleteDto.setIdUser(""); // ID vacío
        incompleteDto.setType(null); // Tipo nulo

        // Act & Assert
        assertThrows(InvalidParameterException.class,
                () -> suscriptionService.createSuscription(incompleteDto),
                "Debería lanzarse una excepción para un DTO incompleto.");

        verifyNoInteractions(kafkaTemplate);
    }


    @Test
    void createSuscriptionInvalidTypeShouldThrowException() {
        // Arrange
        SuscriptionDto invalidTypeDto = new SuscriptionDto();
        invalidTypeDto.setIdUser("123e4567-e89b-12d3-a456-426614174000");
        invalidTypeDto.setType("INVALID_TYPE"); // Tipo no parseable

        // Act & Assert
        assertThrows(InvalidParameterException.class,
                () -> suscriptionService.createSuscription(invalidTypeDto),
                "Debería lanzarse una excepción para un tipo de suscripción inválido.");

        verifyNoInteractions(kafkaTemplate);
    }
}
