package se.iths.weblab2.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.iths.weblab2.dtos.FishDto;
import se.iths.weblab2.services.Service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(FishController.class)
class ControllerTest {
    @MockBean
    Service service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testReturnAllFish() throws Exception {

        when(service.getAllFish()).thenReturn(List.of(new FishDto(1, "Salle", "Salman", "male", 10.1D)));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/fish")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }


}