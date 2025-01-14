package com.usuarios.service.mapper;

import com.usuarios.avro.SuscriptionValue;
import com.usuarios.dto.SuscriptionDto;

public class SuscriptionValueMapper implements Mapper<SuscriptionValue, SuscriptionDto> {
    @Override
    public SuscriptionValue dtoToEntity(SuscriptionDto dto) {
        return SuscriptionValue.newBuilder().setIdUser(dto.getIdUser()).setName(dto.getType()).build();
    }
}
