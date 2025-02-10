package com.suscribers.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SuscriberDto {
    private String id;
    private String username;
    private String email;
    private List<SuscriptionFinal> suscriptions;
    private LocalDateTime date;

}
