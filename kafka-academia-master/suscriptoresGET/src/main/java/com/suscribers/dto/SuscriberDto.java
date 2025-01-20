package com.suscribers.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
public class SuscriberDto {
    private String id;
    private String username;
    private String email;
    private ArrayList<SuscriptionType> suscriptions;
    private LocalDateTime date;

}
