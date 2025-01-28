package com.suscribers.service;

import com.suscribers.dto.SuscriberDto;

import java.util.List;

public interface SuscriberService {

    void saveSuscriber(SuscriberDto suscriber);

    SuscriberDto getSuscriber(String id);

    List<SuscriberDto> getSuscribers();
}
