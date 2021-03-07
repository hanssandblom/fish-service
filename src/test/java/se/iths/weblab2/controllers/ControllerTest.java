package se.iths.weblab2.controllers;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.iths.weblab2.dtos.FishDto;
import se.iths.weblab2.dtos.FishGender;
import se.iths.weblab2.services.Service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FishController.class)
class ControllerTest {
    @MockBean
    Service service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testReturnAllFishSuccess() throws Exception {

        when(service.getAllFish()).thenReturn(List.of(new FishDto(1, "Salle", "Salmon", "male", 10.1D)));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/fish")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void testReturnOneSuccess() throws Exception {

        when(service.getOne(1)).thenReturn(Optional.of(new FishDto(1, "Salle", "Salmon", "male", 10.1D)));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/fish/{id}", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void testCreateFishSuccess() throws Exception {
        FishDto fish = new FishDto(1,"Salle", "Salmon", "male", 10.1D);
        Gson gson = new Gson();
        when(service.createFish(fish)).thenReturn(fish);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/fish")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(fish))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(201);

    }

    @Test
    void testSearchFishByNameSuccess() throws Exception {
        when(service.searchByName("Salle")).thenReturn(List.of(new FishDto(1, "Salle", "Salmon", "Male", 10.1D)));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/fish/search?name=Salle")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void testSearchFishByGenderSuccess() throws Exception {
        when(service.searchByName("Male")).thenReturn(List.of(new FishDto(1, "Salle", "Salmon", "Male", 10.1D)));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/fish/search?gender=male")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void testDeleteFishSuccess() throws Exception {
        FishDto fish = new FishDto(1,"Salle", "Salmon", "male", 10.1D);
        when(service.getOne(1)).thenReturn(Optional.of(new FishDto(1, "Salle", "Salmon", "male", 10.1D)));

        doNothing().when(service).delete(fish.getId());
        mockMvc.perform(
                delete("/fish/{id}", fish.getId()))
                .andExpect(status().isOk());
        verify(service, times(1)).delete(fish.getId());
        verifyNoMoreInteractions(service);
    }

    @Test
    void testReplaceFishSuccess() throws Exception {
        FishDto fish = new FishDto(1,"Salle", "Salmon", "male", 10.1D);
        FishDto newFish = new FishDto(1,"Java", "Shark", "female", 101.1D);
        Gson gson = new Gson();
        when(service.replace(1, fish)).thenReturn(newFish);

        var result = mockMvc.perform(MockMvcRequestBuilders.put("/fish/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(fish))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void testUpdateFishSuccess() throws Exception {
        FishDto fish = new FishDto(1,"Salle", "Salmon", "male", 10.1D);
        FishGender fishGender = new FishGender("female");
        Gson gson = new Gson();

        when(service.updateGender(1, fishGender)).thenReturn(fish);

        var result = mockMvc.perform(MockMvcRequestBuilders.put("/fish/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(fish))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }


}