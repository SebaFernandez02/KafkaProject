package com.suscriptions.service.mapper;

import com.suscribers.avro.SuscriptionKey;
import com.suscriptions.dto.SuscriptionDto;
import org.apache.avro.AvroRuntimeException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SuscriptionKeyMapperTest {

    @Test
    void dtoToEntityValidUuidShouldReturnValidEntity() {
        // Arrange
        String validUuid = UUID.randomUUID().toString();
        SuscriptionDto dto = new SuscriptionDto();
        dto.setIdUser(validUuid);

        SuscriptionKeyMapper mapper = new SuscriptionKeyMapper();

        // Act
        SuscriptionKey result = mapper.dtoToEntity(dto);

        // Assert
        assertNotNull(result, "El objeto SuscriptionKey no debería ser nulo.");
        assertEquals(validUuid, result.getIdUser(), "El campo idUser debería coincidir entre el DTO y la entidad.");
    }

    @Test
    void dtoToEntityInvalidUuidShouldStillMapToEntity() {
        // Arrange
        String invalidUuid = "not-a-valid-uuid";
        SuscriptionDto dto = new SuscriptionDto();
        dto.setIdUser(invalidUuid);

        SuscriptionKeyMapper mapper = new SuscriptionKeyMapper();

        // Act
        SuscriptionKey result = mapper.dtoToEntity(dto);

        // Assert
        assertNotNull(result, "El objeto SuscriptionKey no debería ser nulo.");
        assertEquals(invalidUuid, result.getIdUser(), "El campo idUser debería contener el valor inválido tal como se pasó.");
    }

    @Test
    void dtoToEntityNullUuidShouldThrowException() {
        // Arrange
        SuscriptionDto dto = new SuscriptionDto();
        dto.setIdUser(null);

        SuscriptionKeyMapper mapper = new SuscriptionKeyMapper();

        // Act & Assert
        assertThrows(AvroRuntimeException.class, () -> mapper.dtoToEntity(dto),
                "Debería lanzar una NullPointerException si el idUser del DTO es nulo.");
    }

    @Test
    void dtoToEntityEmptyUuidShouldReturnEntityWithEmptyIdUser() {
        // Arrange
        SuscriptionDto dto = new SuscriptionDto();
        dto.setIdUser("");

        SuscriptionKeyMapper mapper = new SuscriptionKeyMapper();

        // Act
        SuscriptionKey result = mapper.dtoToEntity(dto);

        // Assert
        assertNotNull(result, "El objeto SuscriptionKey no debería ser nulo.");
        assertEquals("", result.getIdUser(), "El campo idUser debería ser vacío cuando el DTO tiene un idUser vacío.");
    }
}
