package com.usuarios.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SuscriptionDto {
    private UUID idUser;
    private SuscriptionType type;
}
