package com.hiberus.cursos.consultadorclases.controller;


import com.hiberus.cursos.consultadorclases.dto.ClaseDTO;
import com.hiberus.cursos.consultadorclases.service.ClasesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/clases")
public class ClasesControllerImpl implements ClasesController {

    @Autowired
    ClasesService clasesService;

    @Override
    @GetMapping("")
    public ResponseEntity<List<ClaseDTO>> getClases() {
       List<ClaseDTO> clases = clasesService.getClases();
       return new ResponseEntity<>(clases, HttpStatus.OK);
    }

}
