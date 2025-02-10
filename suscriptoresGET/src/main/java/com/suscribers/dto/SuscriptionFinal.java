package com.suscribers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SuscriptionFinal {
    private String type;
    private String date;
}
