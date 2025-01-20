package com.suscriptions.service.mapper;

import com.suscriptions.avro.SuscriptionValue;
import com.suscriptions.dto.SuscriptionDto;
import com.suscriptions.dto.SuscriptionType;
import es.curso.kafka.avro.Tipo;

public class SuscriptionValueMapper implements Mapper<SuscriptionValue, SuscriptionDto> {
    @Override
    public SuscriptionValue dtoToEntity(SuscriptionDto dto) {
        return SuscriptionValue.newBuilder().setIdUser(dto.getIdUser()).setName(Tipo.valueOf(SuscriptionType.parse(dto.getType()).name())).build();
    }
}
