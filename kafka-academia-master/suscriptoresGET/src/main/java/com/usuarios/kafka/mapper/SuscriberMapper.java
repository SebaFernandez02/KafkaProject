package com.usuarios.kafka.mapper;

import com.usuarios.avro.SuscriberFinalKey;
import com.usuarios.avro.SuscriberFinalValue;
import com.usuarios.avro.Suscription;
import com.usuarios.dto.SuscriberDto;
import com.usuarios.dto.SuscriptionType;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SuscriberMapper implements Mapper<SuscriberFinalKey, SuscriberFinalValue, SuscriberDto>{


    @Override
    public SuscriberDto toDto(SuscriberFinalKey key, SuscriberFinalValue value) {

        SuscriberDto dto = SuscriberDto.builder()
                .id(key.getId())
                .username(value.getUsername())
                .email(value.getEmail())
                .suscriptions(parse(value.getSuscriptions()))
                .date(LocalDateTime.parse(value.getDate()))
                .build();
        return dto;
    }

    private ArrayList<SuscriptionType> parse(List<Suscription> suscriptions){
        ArrayList<SuscriptionType> suscriptionTypes = new ArrayList<>();

        for (Suscription suscription : suscriptions){
            suscriptionTypes.add(SuscriptionType.valueOf(suscription.getType()));
        }
        return suscriptionTypes;
    }
}
