package com.users.service.mapper;

import com.usuarios.avro.UserKey;
import com.users.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserKeyMapperTest {

    private final UserKeyMapper userKeyMapper = new UserKeyMapper();

    @Test
    void testDtoToEntity_Success() {
        // Arrange
        UserDto userDto = new UserDto();
        UUID userId = UUID.randomUUID();
        userDto.setId(userId);

        // Act
        UserKey userKey = userKeyMapper.dtoToEntity(userDto);

        // Assert
        assertEquals(userId.toString(), userKey.getId(), "El ID de UserKey debería coincidir con el ID del UserDto.");
    }

    @Test
    void testDtoToEntity_NullDto() {
        // Act & Assert
        assertThrows(NullPointerException.class,
                () -> userKeyMapper.dtoToEntity(null),
                "Debería lanzar una excepción NullPointerException cuando el UserDto es nulo."
        );
    }

    @Test
    void testDtoToEntity_NullId() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setId(null);

        // Act & Assert
        assertThrows(NullPointerException.class,
                () -> userKeyMapper.dtoToEntity(userDto),
                "Debería lanzar una excepción NullPointerException cuando el ID es nulo."
        );
    }
}
