package com.usuarios;

import com.usuarios.avro.SuscriptionsValue;
import com.usuarios.kafkaConsumer.Initializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InitializerTest {

    @InjectMocks
    private Initializer initializer;

    @Test
    void testApply() {
        // Ejecutar
        SuscriptionsValue result = initializer.apply();

        // Verificar
        Assertions.assertNotNull(result.getSuscriptions());
    }
}

