package com.hiberus.cursos.consultadorclases.service;

import com.hiberus.cursos.consultadorclases.dto.ClaseDTO;

import java.util.List;

public interface ClasesService {

    List<ClaseDTO> getClases();

    void guardarClase(ClaseDTO clase);
}
