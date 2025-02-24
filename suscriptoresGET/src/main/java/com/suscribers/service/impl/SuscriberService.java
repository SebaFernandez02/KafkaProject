package com.suscribers.service.impl;

import com.suscribers.dto.SuscriberDto;
import com.suscribers.model.Suscriber;
import com.suscribers.repository.SuscribersMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.NotFoundException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SuscriberService implements com.suscribers.service.SuscriberService {


    @Autowired
    private SuscribersMongoRepository mongoRepository;




    @Override
    @Transactional
    public void saveSuscriber(SuscriberDto dto) {
        try {
            if (dto == null) {
                log.error("El DTO recibido es nulo");
                throw new InvalidParameterException("El DTO no puede ser nulo");
            }

            if (dto.getId() == null || dto.getUsername() == null || dto.getEmail() == null) {
                log.error("Faltan datos en el DTO: " + dto);
                throw new InvalidParameterException("Datos incompletos para el suscriptor");
            }

            Suscriber suscriber = Suscriber.builder()
                    .id(UUID.fromString(dto.getId()))
                    .username(dto.getUsername())
                    .email(dto.getEmail())
                    .suscriptions(dto.getSuscriptions())
                    .date(dto.getDate())
                    .build();

            log.info("[ServiceSuscriber] Guardando suscriptor en BBDD");
            mongoRepository.save(suscriber);
            log.info("Suscriptor guardado en BBDD");

        } catch (Exception e) {
            log.error("Error al guardar el suscriptor", e);
            throw new InvalidParameterException("Error al guardar el suscriptor");
        }
    }

    @Override
    public SuscriberDto getSuscriber(String id) {
        log.info("[ServiceSuscriber] Obteniendo suscriptor desde la BBDD");

        Suscriber suscriber = mongoRepository.findById(UUID.fromString(id)).orElseThrow(() -> new NotFoundException("no existe un usuario con id: " + id));

        return suscriber == null ? null : suscriber.toDto();
    }

    @Override
    public List<SuscriberDto> getSuscribers() {

        log.info("[ServiceSuscriber] Obteniendo suscriptores desde la BBDD");

        return mongoRepository.findAll().stream().map(Suscriber::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SuscriberDto> getActiveSuscribers() {
        List<SuscriberDto> suscribers = mongoRepository.findAll()
                .stream()
                .filter(x -> x.getDate().getDayOfYear() == (LocalDateTime.now().getDayOfYear()))
                .map(Suscriber::toDto)
                .collect(Collectors.toList());

        suscribers.forEach(x -> x.setSuscriptions(new ArrayList<>(x.getSuscriptions().stream().filter(y -> LocalDateTime.parse(y.getDate()).getDayOfYear() == LocalDateTime.now().getDayOfYear()).collect(Collectors.toList()))));
        return suscribers;
    }

    @Override
    public List<SuscriberDto> getByPark(String type) {
        List<SuscriberDto> suscribers = mongoRepository.findAll()
                .stream()
                .filter(x -> x.getSuscriptions().stream().anyMatch(y -> y.getType().equals(type.toUpperCase(Locale.ROOT))
                        && LocalDateTime.parse(y.getDate()).getDayOfYear() == LocalDateTime.now().getDayOfYear()))
                .map(Suscriber::toDto)
                .collect(Collectors.toList());

        suscribers.forEach(x -> x.setSuscriptions(new ArrayList<>(x.getSuscriptions().stream().filter(y -> LocalDateTime.parse(y.getDate()).getDayOfYear() == LocalDateTime.now().getDayOfYear()).collect(Collectors.toList()))));
        return suscribers;
    }


}
