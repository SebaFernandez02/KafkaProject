package com.usuarios.service;

import com.usuarios.dto.SuscriberDto;
import com.usuarios.model.Suscriber;

import java.util.List;

public interface SuscriberService {

    void saveSuscriber(SuscriberDto suscriber);

    SuscriberDto getSuscriber(String id);

    List<SuscriberDto> getSuscribers();
}
