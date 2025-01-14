package com.usuarios.dto;

import lombok.extern.slf4j.Slf4j;

import java.security.InvalidParameterException;
import java.util.Locale;

@Slf4j
public enum SuscriptionType {
    ACUATICO,
    AEREO,
    KARTING,
    LASER,
    TIROLESA,
    INFANTIL;

    public SuscriptionType parse(String type){
        try{
            return SuscriptionType.valueOf(type.toUpperCase(Locale.ROOT));
        }catch (Exception e){
            log.error("Tipo de suscripcion invalido");
            throw new InvalidParameterException("Tipo de suscripcion invalido");
        }
    }
}
