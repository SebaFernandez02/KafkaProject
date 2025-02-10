package com.suscribers.model;

import com.suscribers.dto.SuscriberDto;
import com.suscribers.dto.SuscriptionFinal;
import com.suscribers.dto.SuscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j

@Document(collection = "suscriptores")
public class Suscriber {

    @MongoId
    private UUID id;

    private String username;

    private String email;

    private List<SuscriptionFinal> suscriptions;

    private LocalDateTime date;

    public SuscriberDto toDto(){
        log.info("toDto");
        return SuscriberDto.builder()
                .id(this.getId().toString())
                .username(this.getUsername())
                .email(this.getEmail())
                .suscriptions(this.getSuscriptions())
                .date(this.getDate())
                .build();
    }
}
