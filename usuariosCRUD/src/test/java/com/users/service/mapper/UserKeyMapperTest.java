package com.users.service.mapper;

import com.usuarios.avro.UserKey;
import com.users.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserKeyMapperTest {

    private final UserKeyMapper userKeyMapper = new UserKeyMapper();

    @Test
    void testDtoToEntitySuccess() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDto userDto = UserDto.builder().id(userId.toString()).username("testuser").email("testuser@example.com").build();

        // Act
        UserKey userKey = userKeyMapper.dtoToEntity(userDto);

        // Assert
        assertEquals(userId.toString(), userKey.getId(), "El ID de UserKey debería coincidir con el ID del UserDto.");
    }
}
