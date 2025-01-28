package com.suscribers.kafkaConsumer.mapper;

import com.suscribers.avro.SuscriberFinalKey;
import com.suscribers.avro.SuscriberFinalValue;
import com.suscribers.avro.Suscription;
import com.suscribers.dto.SuscriberDto;
import com.suscribers.dto.SuscriptionType;

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
