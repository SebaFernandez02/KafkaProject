package com.suscriptions.service.mapper;

import com.suscriptions.avro.SuscriptionKey;
import com.suscriptions.dto.SuscriptionDto;

public class SuscriptionKeyMapper implements Mapper<SuscriptionKey, SuscriptionDto> {

    @Override
    public SuscriptionKey dtoToEntity(SuscriptionDto dto) {
        return SuscriptionKey.newBuilder().setIdUser(dto.getIdUser()).build();

    }
}
