package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.adapter.LocalDateAdapter;
import com.dent.dentclinicapp.adapter.LocalDateTimeAdapter;
import com.dent.dentclinicapp.domain.*;
import com.dent.dentclinicapp.mapper.AppointmentMapper;
import com.dent.dentclinicapp.proxy.ProxyInterface;
import com.dent.dentclinicapp.service.AppointmentService;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService service;

    @MockBean
    private AppointmentMapper mapper;

    @MockBean
    private ProxyInterface proxy;

    private final Appointment appointment1 = new Appointment(1L, "Name1", "Surname1", "111", "email1", LocalDateTime.of(2022, 2, 2, 2, 2),
                    new Dentist(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1), List.of()),
                    new Services(1L, "Description1", 111.1, List.of()));
    private final Appointment appointment2 = new Appointment(2L, "Name2", "Surname2", "222", "email2", LocalDateTime.of(2022, 2, 2, 2, 2),
                    new Dentist(2L, "DentistName2", "DentistSurname2", LocalDate.of(1998, 2,2), List.of()),
                    new Services(2L, "Description2", 222.2, List.of()));
    private final Appointment appointment3 = new Appointment(3L, "Name3", "Surname3", "333", "email3", LocalDateTime.of(2022, 2, 2, 2, 2),
                    new Dentist(3L, "DentistName3", "DentistSurname3", LocalDate.of(1999, 3,3), List.of()),
                    new Services(3L, "Description3", 333.3, List.of()));

    private final List<Appointment> appointments = List.of(appointment1 ,appointment2, appointment3);

    private final AppointmentDto appointmentDto1 = new AppointmentDto(1L, "Name1", "Surname1", "111", "email1", LocalDateTime.of(2022, 2, 2, 2, 2),
            new DentistDto(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1)),
            new ServicesDto(1L, "Description1", 111.1));
    private final AppointmentDto appointmentDto2 = new AppointmentDto(2L, "Name2", "Surname2", "222", "email2", LocalDateTime.of(2022, 2, 2, 2, 2),
            new DentistDto(2L, "DentistName2", "DentistSurname2", LocalDate.of(1998, 2,2)),
            new ServicesDto(2L, "Description2", 222.2));
    private final AppointmentDto appointmentDto3 = new AppointmentDto(3L, "Name3", "Surname3", "333", "email3", LocalDateTime.of(2022, 2, 2, 2, 2),
            new DentistDto(3L, "DentistName3", "DentistSurname3", LocalDate.of(1999, 3,3)),
            new ServicesDto(3L, "Description3", 333.3));

    private final List<AppointmentDto> appointmentDtos = List.of(appointmentDto1, appointmentDto2, appointmentDto3);

    @Test
    void shouldFetchEmptyAppointmentBoards() throws Exception {
        // Given
        List<Appointment> appointments = new ArrayList<>();
        when(mapper.mapToAppointmentDtoList(appointments)).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/appointment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchAppointmentBoards() throws Exception {
        // Given
        when(service.getAllAppointments()).thenReturn(appointments);
        when(mapper.mapToAppointmentDtoList(appointments)).thenReturn(appointmentDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/appointment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Name2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].surname", Matchers.is("Surname3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pesel", Matchers.is("111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("email2")))
                /*Dentist*/
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dentist.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dentist.name", Matchers.is("DentistName2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].dentist.surname", Matchers.is("DentistSurname3")))
                /*Service*/
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].service.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].service.cost", Matchers.is(222.2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].service.description", Matchers.is("Description3")));
    }

    @Test
    void shouldFetchAppointment() throws Exception {
        // Given
        when(service.getAppointment(any(long.class))).thenReturn(appointment1);
        when(mapper.mapToAppointmentDto(any(Appointment.class))).thenReturn(appointmentDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/appointment" + "/1")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Surname1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel", Matchers.is("111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("email1")));
    }

    @Test
    void shouldDeleteAppointment() throws Exception {
        // Given
        when(service.getAppointment(any(long.class))).thenReturn(appointment1);
        doNothing().when(service).deleteAppointment(any(Appointment.class));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/appointment" + "/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateAppointment() throws Exception {
        // Given
        when(mapper.mapToAppointment(any(AppointmentDto.class))).thenReturn(appointment1);
        when(service.saveAppointment(any(Appointment.class))).thenReturn(appointment1);
        doNothing().when(proxy).sendAnEmail(any(AppointmentDto.class));



        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(appointmentDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateAppointment() throws Exception {
        // Given
        when(service.getAppointment(any(long.class))).thenReturn(appointment1);
        when(service.saveAppointment(any(Appointment.class))).thenReturn(appointment1);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(appointmentDto1);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Surname1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel", Matchers.is("111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("email1")));
    }
}