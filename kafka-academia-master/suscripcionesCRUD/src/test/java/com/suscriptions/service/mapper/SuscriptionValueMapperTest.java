package com.suscriptions.service.mapper;

import com.suscribers.avro.SuscriptionValue;
import com.suscriptions.dto.SuscriptionDto;
import com.suscriptions.dto.SuscriptionType;
import com.suscribers.avro.*;
import org.apache.avro.AvroRuntimeException;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class SuscriptionValueMapperTest {

    @Test
    void dtoToEntity_ValidInput_ShouldReturnValidEntity() {
        // Arrange
        String validIdUser = "123e4567-e89b-12d3-a456-426614174000";
        SuscriptionDto dto = new SuscriptionDto();
        dto.setIdUser(validIdUser);
        dto.setType(SuscriptionType.parse("aCuatiCo").toString());

        SuscriptionValueMapper mapper = new SuscriptionValueMapper();

        // Act
        SuscriptionValue result = mapper.dtoToEntity(dto);

        // Assert
        assertEquals(validIdUser, result.getIdUser(), "El campo idUser debería coincidir con el del DTO.");
        assertEquals(Tipo.ACUATICO, result.getName(), "El campo name debería coincidir con el tipo mapeado.");
    }

    @Test
    void dtoToEntity_InvalidType_ShouldThrowException() {
        // Arrange
        SuscriptionDto dto = new SuscriptionDto();
        dto.setIdUser("123e4567-e89b-12d3-a456-426614174000");
        dto.setType("INVALID_TYPE");

        SuscriptionValueMapper mapper = new SuscriptionValueMapper();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> mapper.dtoToEntity(dto));
    }

    @Test
    void dtoToEntity_NullIdUser_ShouldThrowException() {
        // Arrange
        SuscriptionDto dto = new SuscriptionDto();
        dto.setIdUser(null);
        dto.setType("ACUATICO");

        SuscriptionValueMapper mapper = new SuscriptionValueMapper();

        // Act & Assert
        assertThrows(AvroRuntimeException.class, () -> mapper.dtoToEntity(dto));
    }

    @Test
    void dtoToEntity_NullType_ShouldThrowException() {
        // Arrange
        SuscriptionDto dto = new SuscriptionDto();
        dto.setIdUser("123e4567-e89b-12d3-a456-426614174000");
        dto.setType(null);

        SuscriptionValueMapper mapper = new SuscriptionValueMapper();

        // Act & Assert
        assertThrows(InvalidParameterException.class, () -> mapper.dtoToEntity(dto));
    }
}
