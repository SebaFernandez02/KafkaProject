package com.suscribers.service.impl;

import com.suscribers.dto.SuscriberDto;
import com.suscribers.model.Suscriber;
import com.suscribers.repository.SuscribersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SuscriberService implements com.suscribers.service.SuscriberService {

    @Autowired
    private SuscribersRepository suscribersRepository;




    @Override
    @Transactional
    public void saveSuscriber(SuscriberDto dto) {

        Suscriber suscriber = Suscriber.builder()
                .id(UUID.fromString(dto.getId()))
                .username(dto.getUsername())
                .email(dto.getEmail())
                .suscriptions(dto.getSuscriptions())
                .date(Timestamp.valueOf(dto.getDate()))
                .build();

        log.info("[ServiceSuscriber] Guardando suscriptor en BBDD");
        suscribersRepository.save(suscriber);
        log.info("Suscriptor guardado en BBDD");
    }

    @Override
    public SuscriberDto getSuscriber(String id) {
        log.info("[ServiceSuscriber] Obteniendo suscriptor desde la BBDD");

        Suscriber suscriber = suscribersRepository.findById(UUID.fromString(id)).orElseThrow(() -> new EntityNotFoundException("No se encontro un suscriptor con el id: " + id));

        return suscriber.toDto();
    }

    @Override
    public List<SuscriberDto> getSuscribers() {

        return suscribersRepository.findAll().stream().map(Suscriber::toDto).collect(Collectors.toList());
    }


}
