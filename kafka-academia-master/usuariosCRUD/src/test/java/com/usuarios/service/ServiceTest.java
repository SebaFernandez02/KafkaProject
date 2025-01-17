package com.usuarios.service;

import com.usuarios.avro.UserKey;
import com.usuarios.avro.UserValue;
import com.usuarios.dto.UserDto;
import com.usuarios.service.impl.UserServiceImpl;
import com.usuarios.service.mapper.UserKeyMapper;
import com.usuarios.service.mapper.UserValueMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import java.security.InvalidParameterException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private KafkaTemplate<UserKey, UserValue> kafkaTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUsername("testuser");
        userDto.setEmail("testuser@example.com");

        // Act
        userService.createUser(userDto);

        UserValueMapper userValueMapper = spy(new UserValueMapper());
        UserValue userValue = userValueMapper.dtoToEntity(userDto);

        UserKeyMapper userKeyMapper = spy(new UserKeyMapper());
        UserKey userKey = userKeyMapper.dtoToEntity(userDto);

        // Assert
        assertNotNull(userDto.getId(), "El ID del usuario debería haberse generado.");
        verify(kafkaTemplate, times(1)).send("users", userKey, userValue);
    }

    @Test
    void testCreateUser_NullUser() {
        // Act & Assert
        InvalidParameterException exception = assertThrows(
                InvalidParameterException.class,
                () -> userService.createUser(null),
                "Debería lanzar una excepción cuando el usuario es nulo"
        );
    }

    @Test
    void testCreateUser_BlankUsername() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUsername("   "); // Username en blanco
        userDto.setEmail("testuser@example.com");

        // Act & Assert
        InvalidParameterException exception = assertThrows(
                InvalidParameterException.class,
                () -> userService.createUser(userDto),
                "Debería lanzar una excepción cuando el username está en blanco"
        );
        assertEquals("Usuario nulo o incompleto", exception.getMessage());
    }

    @Test
    void testCreateUser_BlankEmail() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUsername("testuser");
        userDto.setEmail("   "); // Email en blanco

        // Act & Assert
        InvalidParameterException exception = assertThrows(
                InvalidParameterException.class,
                () -> userService.createUser(userDto),
                "Debería lanzar una excepción cuando el email está en blanco"
        );
        assertEquals("Usuario nulo o incompleto", exception.getMessage());
    }

    @Test
    void testCreateUser_InvalidKafkaSend() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setUsername("testuser");
        userDto.setEmail("testuser@example.com");

        doThrow(new RuntimeException("Kafka error"))
                .when(kafkaTemplate)
                .send(any(), any(), any());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.createUser(userDto),
                "Debería lanzar una excepción cuando Kafka falla"
        );
        assertEquals("Kafka error", exception.getMessage());
    }
}
