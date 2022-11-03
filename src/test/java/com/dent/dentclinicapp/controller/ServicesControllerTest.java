package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.*;
import com.dent.dentclinicapp.mapper.ServicesMapper;
import com.dent.dentclinicapp.service.ServicesService;
import com.google.gson.Gson;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(ServicesController.class)
class ServicesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicesService service;

    @MockBean
    private ServicesMapper mapper;

    private final Services service1 = new Services(1L, "Description1", 111.1);
    private final Services service2 = new Services(2L, "Description2", 222.2);
    private final Services service3 = new Services(3L, "Description3", 333.3);

    private final List<Services> services = List.of(service1, service2, service3);

    private final ServicesDto serviceDto1 = new ServicesDto(1L, "Description1", 111.1);
    private final ServicesDto serviceDto2 = new ServicesDto(2L, "Description2", 222.2);
    private final ServicesDto serviceDto3 = new ServicesDto(3L, "Description3", 333.3);

    private final List<ServicesDto> servicesDtos = List.of(serviceDto1, serviceDto2, serviceDto3);

    @Test
    void shouldFetchEmptyServiceBoards() throws Exception {
        // Given
        List<Services> servicesList = new ArrayList<>();
        when(mapper.mapToServicesDtoList(servicesList)).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/service")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchServiceBoards() throws Exception {
        // Given
        when(service.getAllServices()).thenReturn(services);
        when(mapper.mapToServicesDtoList(services)).thenReturn(servicesDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/service")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("Description1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.is("Description2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].description", Matchers.is("Description3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cost", Matchers.is(111.1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].cost", Matchers.is(222.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].cost", Matchers.is(333.3)));
    }

    @Test
    void shouldFetchService() throws Exception {
        // Given
        when(service.getService(any(long.class))).thenReturn(service1);
        when(mapper.mapToServicesDto(any(Services.class))).thenReturn(serviceDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/service" + "/1")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("Description1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost", Matchers.is(111.1)));
    }

    @Test
    void shouldDeleteService() throws Exception {
        // Given
        when(service.getService(any(long.class))).thenReturn(service1);
        doNothing().when(service).deleteService(any(Services.class));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/service" + "/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateService() throws Exception {
        // Given
        when(mapper.mapToServices(any(ServicesDto.class))).thenReturn(service1);
        when(service.saveService(any(Services.class))).thenReturn(service1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(serviceDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateService() throws Exception {
        // Given
        when(service.getService(any(long.class))).thenReturn(service1);
        when(service.saveService(any(Services.class))).thenReturn(service1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(serviceDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("Description1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost", Matchers.is(111.1)));
    }
}