package com.usuarios.service.mapper;

import com.usuarios.avro.UserValue;
import com.usuarios.dto.UserDto;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.AvroRuntimeException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserValueMapperTest {

    private final UserValueMapper userValueMapper = new UserValueMapper();

    @Test
    void testDtoToEntity_Success() {
        // Arrange
        UserDto userDto = new UserDto();
        UUID userId = UUID.randomUUID();
        userDto.setId(userId);
        userDto.setUsername("test_user");
        userDto.setEmail("test_user@example.com");

        // Act
        UserValue userValue = userValueMapper.dtoToEntity(userDto);

        // Assert
        assertEquals(userId.toString(), userValue.getId(), "El ID de UserValue debería coincidir con el ID del UserDto.");
        assertEquals("test_user", userValue.getUsername(), "El username debería coincidir con el del UserDto.");
        assertEquals("test_user@example.com", userValue.getEmail(), "El email debería coincidir con el del UserDto.");
    }

    @Test
    void testDtoToEntity_NullDto() {
        // Act & Assert
        assertThrows(
                NullPointerException.class,
                () -> userValueMapper.dtoToEntity(null),
                "Debería lanzar una excepción NullPointerException cuando el UserDto es nulo."
        );
    }

    @Test
    void testDtoToEntity_NullId() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setId(null);
        userDto.setUsername("test_user");
        userDto.setEmail("test_user@example.com");

        // Act & Assert
        assertThrows(NullPointerException.class,
                () -> userValueMapper.dtoToEntity(userDto),
                "Debería lanzar una excepción NullPointerException cuando el ID es nulo."
        );
    }

    @Test
    void testDtoToEntity_NullUsername() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setId(UUID.randomUUID());
        userDto.setUsername(null);
        userDto.setEmail("test_user@example.com");

        // Assert
        assertThrows(AvroRuntimeException.class, () -> userValueMapper.dtoToEntity(userDto));
    }

    @Test
    void testDtoToEntity_NullEmail() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setId(UUID.randomUUID());
        userDto.setUsername("test_user");
        userDto.setEmail(null);


        // Assert
        assertThrows(AvroRuntimeException.class, () -> userValueMapper.dtoToEntity(userDto));
    }

    @Test
    void testDtoToEntity_EmptyFields() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setId(UUID.randomUUID());
        userDto.setUsername("");
        userDto.setEmail("");

        // Act
        UserValue userValue = userValueMapper.dtoToEntity(userDto);

        // Assert
        assertEquals("", userValue.getUsername(), "El username de UserValue debería estar vacío si el UserDto tiene un username vacío.");
        assertEquals("", userValue.getEmail(), "El email de UserValue debería estar vacío si el UserDto tiene un email vacío.");
    }
}

