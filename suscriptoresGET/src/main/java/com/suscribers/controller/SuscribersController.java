package com.suscribers.controller;

import com.suscribers.dto.SuscriberDto;
import com.suscribers.service.SuscriberService;
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

    @GetMapping("/getSuscribers")
    public ResponseEntity<List<SuscriberDto>> getSuscriberss(){
        try {
            log.info("try");
            List<SuscriberDto> suscriberDtos = suscriberService.getSuscribers();
            log.info("tengo la lista");
            return new ResponseEntity<>(suscriberDtos, HttpStatus.OK);

        }catch (Exception e){
            log.error("Error al obtener suscriptores", e);
            log.info("catch");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getSusciber/{id}")
    public ResponseEntity<SuscriberDto> getSuscriber(@PathVariable String id){
        try {
            SuscriberDto suscriber = suscriberService.getSuscriber(id);

            return new ResponseEntity<>(suscriber, HttpStatus.OK);

        }catch (Exception e){

            log.info("un suscriber");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/activeSuscribers")
    public ResponseEntity<List<SuscriberDto>> activeSuscribers() {
        try {
            log.info("try");
            List<SuscriberDto> suscriberDtos = suscriberService.getActiveSuscribers();
            log.info("tengo la lista");
            return new ResponseEntity<>(suscriberDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
