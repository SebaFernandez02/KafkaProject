package com.usuarios.controller;

import com.usuarios.dto.SuscriptionDto;
import com.usuarios.service.SuscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SuscriptionControllerTest {

    @Mock
    private SuscriptionService suscriptionService;

    @InjectMocks
    private SuscriptionController suscriptionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createSuscription_ValidInput_ShouldReturnCreatedStatus() {
        // Arrange
        SuscriptionDto validDto = new SuscriptionDto();
        validDto.setIdUser("123e4567-e89b-12d3-a456-426614174000");
        validDto.setType("PREMIUM");

        doNothing().when(suscriptionService).createSuscription(validDto);

        // Act
        ResponseEntity<SuscriptionDto> response = suscriptionController.createSuscription(validDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "El estado HTTP debería ser 201 (CREATED).");
        verify(suscriptionService, times(1)).createSuscription(validDto);
    }

    @Test
    void createSuscription_ServiceThrowsException_ShouldReturnBadRequestStatus() {
        // Arrange
        SuscriptionDto validDto = new SuscriptionDto();
        validDto.setIdUser("123e4567-e89b-12d3-a456-426614174000");
        validDto.setType("ACUATICO");

        doThrow(new RuntimeException("Error interno")).when(suscriptionService).createSuscription(validDto);

        // Act
        ResponseEntity<SuscriptionDto> response = suscriptionController.createSuscription(validDto);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode(), "El estado HTTP debería ser 400 (BAD_REQUEST).");
        verify(suscriptionService, times(1)).createSuscription(validDto);
    }

}
