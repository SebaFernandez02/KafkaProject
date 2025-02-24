package com.users.service.mapper;

import com.usuarios.avro.UserValue;
import com.users.dto.UserDto;
import org.apache.avro.AvroRuntimeException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserValueMapperTest {

    private final UserValueMapper userValueMapper = new UserValueMapper();

    @Test
    void testDtoToEntitySuccess() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = UserDto.builder().id(userId.toString()).username("test_user").email("test_user@example.com").build();

        // Act
        UserValue userValue = userValueMapper.dtoToEntity(userDto);

        // Assert
        assertEquals(userId.toString(), userValue.getId(), "El ID de UserValue debería coincidir con el ID del UserDto.");
        assertEquals("test_user", userValue.getUsername(), "El username debería coincidir con el del UserDto.");
        assertEquals("test_user@example.com", userValue.getEmail(), "El email debería coincidir con el del UserDto.");
    }
}

