package com.suscribers.test;

import com.suscribers.dto.SuscriberDto;
import com.suscribers.dto.SuscriptionType;
import com.suscribers.model.Suscriber;
import com.suscribers.repository.SuscribersRepository;
import com.suscribers.service.impl.SuscriberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class SuscriberServiceTest {

    @InjectMocks
    private SuscriberService suscriberService;

    @Mock
    private SuscribersRepository suscribersRepository;

    @Test
    void saveSuscriberSavesSuccessfully() {
        SuscriberDto dto = SuscriberDto.builder()
                .id(UUID.randomUUID().toString())
                .username("TestUser")
                .email("test@example.com")
                .suscriptions(new ArrayList<>(List.of(SuscriptionType.AEREO)))
                .date(LocalDateTime.now())
                .build();

        Suscriber suscriber = Suscriber.builder()
                .id(UUID.fromString(dto.getId()))
                .username(dto.getUsername())
                .email(dto.getEmail())
                .suscriptions(new ArrayList<>(List.of(SuscriptionType.AEREO)))
                .date(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        Mockito.when(suscribersRepository.save(Mockito.any(Suscriber.class))).thenReturn(suscriber);

        suscriberService.saveSuscriber(dto);

        Mockito.verify(suscribersRepository, Mockito.times(1)).save(Mockito.any(Suscriber.class));
    }

    @Test
    void getSuscriberReturnsCorrectDto() {
        String id = UUID.randomUUID().toString();

        Suscriber suscriber = Suscriber.builder()
                .id(UUID.fromString(id))
                .username("TestUser")
                .email("test@example.com")
                .suscriptions(new ArrayList<>(List.of(SuscriptionType.AEREO)))
                .date(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        Mockito.when(suscribersRepository.findById(UUID.fromString(id))).thenReturn(Optional.of(suscriber));

        SuscriberDto result = suscriberService.getSuscriber(id);

        Assertions.assertEquals(suscriber.getId().toString(), result.getId());
    }

    @Test
    void getSuscriberThrowsExceptionWhenNotFound() {
        String id = UUID.randomUUID().toString();

        Mockito.when(suscribersRepository.findById(UUID.fromString(id))).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> suscriberService.getSuscriber(id));
    }

    @Test
    void getSuscribersReturnsList() {
        List<Suscriber> suscribers = List.of(
                Suscriber.builder().id(UUID.randomUUID()).username("User1").email("user1@example.com").suscriptions(new ArrayList<>(List.of(SuscriptionType.AEREO))).date(Timestamp.valueOf(LocalDateTime.now())).build(),
                Suscriber.builder().id(UUID.randomUUID()).username("User2").email("user2@example.com").suscriptions(new ArrayList<>(List.of(SuscriptionType.KARTING))).date(Timestamp.valueOf(LocalDateTime.now())).build());

        Mockito.when(suscribersRepository.findAll()).thenReturn(suscribers);

        List<SuscriberDto> result = suscriberService.getSuscribers();

        Assertions.assertEquals(suscribers.size(), result.size());
    }
}
