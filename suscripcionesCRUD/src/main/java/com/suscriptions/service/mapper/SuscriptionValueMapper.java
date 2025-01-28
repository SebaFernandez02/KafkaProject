package com.suscriptions.service.mapper;

import com.suscribers.avro.SuscriptionValue;
import com.suscriptions.dto.SuscriptionDto;
import com.suscriptions.dto.SuscriptionType;
import com.suscribers.avro.*;

public class SuscriptionValueMapper implements Mapper<SuscriptionValue, SuscriptionDto> {
    @Override
    public SuscriptionValue dtoToEntity(SuscriptionDto dto) {
        return SuscriptionValue.newBuilder().setIdUser(dto.getIdUser()).setName(Tipo.valueOf(SuscriptionType.parse(dto.getType()).name())).build();
    }
}
