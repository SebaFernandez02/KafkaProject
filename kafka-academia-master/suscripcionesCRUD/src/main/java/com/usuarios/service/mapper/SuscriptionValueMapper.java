package com.usuarios.service.mapper;

import com.usuarios.avro.SuscriptionValue;
import com.usuarios.dto.SuscriptionDto;
import com.usuarios.dto.SuscriptionType;
import es.curso.kafka.avro.Tipo;

public class SuscriptionValueMapper implements Mapper<SuscriptionValue, SuscriptionDto> {
    @Override
    public SuscriptionValue dtoToEntity(SuscriptionDto dto) {
        return SuscriptionValue.newBuilder().setIdUser(dto.getIdUser()).setName(Tipo.valueOf(SuscriptionType.parse(dto.getType()).name())).build();
    }
}
