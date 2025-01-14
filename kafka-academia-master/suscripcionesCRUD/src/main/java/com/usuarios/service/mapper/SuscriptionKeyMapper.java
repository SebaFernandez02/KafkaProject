package com.usuarios.service.mapper;

import com.usuarios.avro.SuscriptionKey;
import com.usuarios.dto.SuscriptionDto;

public class SuscriptionKeyMapper implements Mapper<SuscriptionKey, SuscriptionDto> {

    @Override
    public SuscriptionKey dtoToEntity(SuscriptionDto dto) {
        return SuscriptionKey.newBuilder().setIdUser(dto.getIdUser().toString()).build();

    }
}
