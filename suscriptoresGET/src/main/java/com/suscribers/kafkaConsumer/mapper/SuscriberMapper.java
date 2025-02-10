package com.suscribers.kafkaConsumer.mapper;

import com.suscribers.avro.SuscriberFinalKey;
import com.suscribers.avro.SuscriberFinalValue;
import com.suscribers.avro.Suscription;
import com.suscribers.dto.SuscriberDto;
import com.suscribers.dto.SuscriptionFinal;
import com.suscribers.dto.SuscriptionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SuscriberMapper implements Mapper<SuscriberFinalKey, SuscriberFinalValue, SuscriberDto>{


    @Override
    public SuscriberDto toDto(SuscriberFinalKey key, SuscriberFinalValue value) {

        ArrayList<Suscription> suscriptions = new ArrayList<>(value.getSuscriptions());

        return SuscriberDto.builder()
                .id(key.getId())
                .username(value.getUsername())
                .email(value.getEmail())
                .suscriptions(suscriptionToDto(value.getSuscriptions()))
                .date(LocalDateTime.parse(value.getDate()))
                .build();
    }

    private ArrayList<SuscriptionType> parse(List<Suscription> suscriptions){
        ArrayList<SuscriptionType> suscriptionTypes = new ArrayList<>();

        for (Suscription suscription : suscriptions){
            suscriptionTypes.add(SuscriptionType.valueOf(suscription.getType()));
        }
        return suscriptionTypes;
    }

    private List<SuscriptionFinal> suscriptionToDto(List<Suscription> suscriptions){
        List<SuscriptionFinal> suscriptionFinals = new ArrayList<>();

        for (Suscription suscription : suscriptions){
            suscriptionFinals.add(SuscriptionFinal.builder().type(suscription.getType()).date(suscription.getDate()).build());
        }
        return suscriptionFinals;
    }
}
