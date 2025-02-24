package com.users.service;

import com.users.model.User;
import com.users.repository.UsuariosRepository;
import com.usuarios.avro.UserKey;
import com.usuarios.avro.UserValue;
import com.users.dto.UserDto;
import com.users.service.impl.UserServiceImpl;
import com.users.service.mapper.UserKeyMapper;
import com.users.service.mapper.UserValueMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UsuariosRepository usuariosRepository;

    @Mock
    private KafkaTemplate<UserKey, UserValue> kafkaTemplate;

    private UserDto userDto;
    private User user;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDto = UserDto.builder().id(UUID.randomUUID().toString()).username("testUser").email("testuser@example.com").build();
        user = new User(UUID.randomUUID(), "testUser", "testuser@example.com");
    }

    @Test
    void testCreateUserSuccess() {
        // Arrange

        // Act
        userService.createUser(userDto);
        UserValueMapper userValueMapper = spy(new UserValueMapper());
        UserValue userValue = userValueMapper.dtoToEntity(userDto);

        UserKeyMapper userKeyMapper = spy(new UserKeyMapper());
        UserKey userKey = userKeyMapper.dtoToEntity(userDto);

        // Assert
        verify(usuariosRepository, times(1)).save(any(User.class));
        assertNotNull(userDto.getId(), "El ID del usuario debería haberse generado.");
        verify(kafkaTemplate, times(1)).send("users", userKey, userValue);
    }

    @Test
    void testCreateUserNullUser() {
        // Act & Assert
        InvalidParameterException exception = assertThrows(
                InvalidParameterException.class,
                () -> userService.createUser(null),
                "Debería lanzar una excepción cuando el usuario es nulo"
        );
    }

    @Test
    void testCreateUserBlankUsername() {
        // Arrange
        userDto.setUsername("");

        // Act & Assert
        InvalidParameterException exception = assertThrows(
                InvalidParameterException.class,
                () -> userService.createUser(userDto),
                "Debería lanzar una excepción cuando el username está en blanco"
        );
        assertEquals("Usuario nulo o incompleto", exception.getMessage());
    }

    @Test
    void testCreateUserBlankEmail() {
        // Arrange
        userDto.setEmail("");

        // Act & Assert
        InvalidParameterException exception = assertThrows(
                InvalidParameterException.class,
                () -> userService.createUser(userDto),
                "Debería lanzar una excepción cuando el email está en blanco"
        );
        assertEquals("Usuario nulo o incompleto", exception.getMessage());
    }

    @Test
    void getUsersReturnsUserList() {
        // Arrange
        when(usuariosRepository.findAll()).thenReturn(Arrays.asList(user));

        // Act
        List<UserDto> result = userService.getUsers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userDto.getUsername(), result.get(0).getUsername());
        verify(usuariosRepository, times(1)).findAll();
    }

    @Test
    void modifyUserSuccess() {
        // Arrange
        when(usuariosRepository.findById(UUID.fromString(userDto.getId()))).thenReturn(Optional.of(user));

        // Act
        userService.modifyUser(userDto);

        // Assert
        verify(usuariosRepository, times(1)).findById(UUID.fromString(userDto.getId()));
        verify(usuariosRepository, times(1)).save(any(User.class));
        verify(kafkaTemplate, times(1)).send(anyString(), any(UserKey.class), any(UserValue.class));
    }

    @Test
    void modifyUserThrowsExceptionWhenUserNotFound() {
        // Arrange
        when(usuariosRepository.findById(UUID.fromString(userDto.getId()))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidParameterException.class, () -> userService.modifyUser(userDto));
        verify(usuariosRepository, times(1)).findById(UUID.fromString(userDto.getId()));
        verify(usuariosRepository, never()).save(any(User.class));
    }

    @Test
    void deleteUserSuccess() {
        // Act
        userService.deleteUser(userDto.getId());

        // Assert
        verify(usuariosRepository, times(1)).deleteById(UUID.fromString(userDto.getId()));
    }

    @Test
    void getUserSuccess() {
        // Arrange
        when(usuariosRepository.findById(UUID.fromString(userDto.getId()))).thenReturn(Optional.of(user));

        // Act
        UserDto result = userService.getUser(UUID.fromString(userDto.getId()).toString());

        // Assert
        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
        verify(usuariosRepository, times(2)).findById(UUID.fromString(userDto.getId())); // Se llama dos veces en el método
    }

    @Test
    void getUserThrowsExceptionWhenNotFound() {
        // Arrange
        when(usuariosRepository.findById(UUID.fromString(userDto.getId()))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidParameterException.class, () -> userService.getUser(UUID.fromString(userDto.getId()).toString()));
        verify(usuariosRepository, times(1)).findById(UUID.fromString(userDto.getId()));
    }

    @Test
    void getUserByEmailSuccess() {
        // Arrange
        when(usuariosRepository.findByEmail(user.getEmail())).thenReturn(user);

        // Act
        UserDto result = userService.getUserByEmail(user.getEmail());

        // Assert
        assertNotNull(result);
        assertEquals(userDto.getEmail(), result.getEmail());
        verify(usuariosRepository, times(1)).findByEmail(user.getEmail());
    }

    @Test
    void getUserByEmailReturnsNullWhenExceptionOccurs() {
        // Arrange
        when(usuariosRepository.findByEmail(user.getEmail())).thenThrow(new RuntimeException("Database error"));

        // Act
        UserDto result = userService.getUserByEmail(user.getEmail());

        // Assert
        assertNull(result);
        verify(usuariosRepository, times(1)).findByEmail(user.getEmail());
    }


}
