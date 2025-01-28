package com.suscribers.model;

import com.suscribers.dto.SuscriberDto;
import com.suscribers.dto.SuscriptionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;


@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "suscribers")
public class Suscriber {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "suscriptions")
    private ArrayList<SuscriptionType> suscriptions;

    @Column(name = "date")
    private Timestamp date;

    public SuscriberDto toDto(){
        log.info("toDto");
        return SuscriberDto.builder()
                .id(this.getId().toString())
                .username(this.getUsername())
                .email(this.getEmail())
                .suscriptions(this.getSuscriptions())
                .date(this.getDate().toLocalDateTime())
                .build();
    }
}
