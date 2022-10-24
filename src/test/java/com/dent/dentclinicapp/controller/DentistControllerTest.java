package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.adapter.LocalDateAdapter;
import com.dent.dentclinicapp.domain.*;
import com.dent.dentclinicapp.mapper.DentistMapper;
import com.dent.dentclinicapp.service.DentistService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(DentistController.class)
class DentistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DentistService service;

    @MockBean
    private DentistMapper mapper;

    private final Dentist dentist1 = new Dentist(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1), List.of());
    private final Dentist dentist2 = new Dentist(2L, "DentistName2", "DentistSurname2", LocalDate.of(1998, 2,2), List.of());
    private final Dentist dentist3 = new Dentist(3L, "DentistName3", "DentistSurname3", LocalDate.of(1999, 3,3), List.of());

    private final List<Dentist> dentists = List.of(dentist1, dentist2, dentist3);

    private final DentistDto dentistDto1 = new DentistDto(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1));
    private final DentistDto dentistDto2 = new DentistDto(2L, "DentistName2", "DentistSurname2", LocalDate.of(1998, 2,2));
    private final DentistDto dentistDto3 = new DentistDto(3L, "DentistName3", "DentistSurname3", LocalDate.of(1999, 3,3));

    private final List<DentistDto> dentistDtos = List.of(dentistDto1, dentistDto2, dentistDto3);

    @Test
    void shouldFetchEmptyDentistBoards() throws Exception {
        // Given
        List<Dentist> dentistsList = new ArrayList<>();
        when(mapper.mapToDentistDtoList(dentistsList)).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/dentist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchDentistBoards() throws Exception {
        // Given
        when(service.getAllDentists()).thenReturn(dentists);
        when(mapper.mapToDentistDtoList(dentists)).thenReturn(dentistDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/dentist")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("DentistName2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].surname", Matchers.is("DentistSurname3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].experience", Matchers.is(LocalDate.of(1997, 1,1).toString())));
    }

    @Test
    void shouldFetchDentist() throws Exception {
        // Given
        when(service.getDentist(any(long.class))).thenReturn(dentist1);
        when(mapper.mapToDentistDto(any(Dentist.class))).thenReturn(dentistDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/dentist" + "/1")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("DentistName1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("DentistSurname1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.experience", Matchers.is(LocalDate.of(1997, 1,1).toString())));
    }

    @Test
    void shouldDeleteDentist() throws Exception {
        // Given
        when(service.getDentist(any(long.class))).thenReturn(dentist1);
        doNothing().when(service).deleteDentist(any(Dentist.class));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/dentist" + "/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateDentist() throws Exception {
        // Given
        when(mapper.mapToDentist(any(DentistDto.class))).thenReturn(dentist1);
        when(service.saveDentist(any(Dentist.class))).thenReturn(dentist1);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(dentistDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/dentist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateDentist() throws Exception {
        // Given
        when(mapper.mapToDentist(any(DentistDto.class))).thenReturn(dentist1);
        when(service.saveDentist(any(Dentist.class))).thenReturn(dentist1);
        when(mapper.mapToDentistDto(any(Dentist.class))).thenReturn(dentistDto1);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(dentistDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/dentist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("DentistName1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("DentistSurname1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.experience", Matchers.is(LocalDate.of(1997, 1,1).toString())));
    }
}