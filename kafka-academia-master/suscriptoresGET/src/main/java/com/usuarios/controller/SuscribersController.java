package com.usuarios.controller;

import com.usuarios.dto.SuscriberDto;
import com.usuarios.model.Suscriber;
import com.usuarios.service.SuscriberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suscribers")
@Slf4j
public class SuscribersController {

    @Autowired
    SuscriberService suscriberService;

    @GetMapping("/{id}")
    public ResponseEntity<SuscriberDto> getSuscriber(@PathVariable String id){
        try {
            SuscriberDto suscriber = suscriberService.getSuscriber(id);

            return new ResponseEntity<>(suscriber, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping
    public ResponseEntity<List<SuscriberDto>> getSuscribers(){
        try {
            return new ResponseEntity<>(suscriberService.getSuscribers(), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
