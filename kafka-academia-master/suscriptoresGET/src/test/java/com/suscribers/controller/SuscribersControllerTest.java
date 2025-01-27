package com.suscribers.controller;

import com.suscribers.controller.SuscribersController;
import com.suscribers.dto.SuscriberDto;
import com.suscribers.service.impl.SuscriberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@WebMvcTest(SuscribersController.class)
class SuscribersControllerTest {

/*    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuscriberService suscriberService;

    @Test
    void getSuscriberReturns200WhenFound() throws Exception {
        String id = "123e4567-e89b-12d3-a456-426614174000";
        SuscriberDto dto = SuscriberDto.builder()
                .id(id)
                .username("testUser")
                .email("test@example.com")
                .build();

        Mockito.when(suscriberService.getSuscriber(id)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/suscribers/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    void getSuscriberReturns404WhenNotFound() throws Exception {
        String id = "invalid-id";

        Mockito.when(suscriberService.getSuscriber(id))
                .thenThrow(new EntityNotFoundException("No se encontro un suscriptor con el id: " + id));

        mockMvc.perform(MockMvcRequestBuilders.get("/suscribers/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getSuscribersReturns200WithList() throws Exception {
        List<SuscriberDto> dtos = List.of(
                SuscriberDto.builder().id("1").username("User1").email("user1@example.com").build(),
                SuscriberDto.builder().id("2").username("User2").email("user2@example.com").build()
        );

        Mockito.when(suscriberService.getSuscribers()).thenReturn(dtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/suscribers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(dtos.size()));
    }*/
}
