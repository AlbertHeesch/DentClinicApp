package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.adapter.LocalDateAdapter;
import com.dent.dentclinicapp.adapter.LocalDateTimeAdapter;
import com.dent.dentclinicapp.domain.*;
import com.dent.dentclinicapp.mapper.AppointmentMapper;
import com.dent.dentclinicapp.proxy.ProxyInterface;
import com.dent.dentclinicapp.service.AppointmentService;
import com.dent.dentclinicapp.service.DentistService;
import com.dent.dentclinicapp.service.ServicesService;
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
    private AppointmentService appointmentService;

    @MockBean
    private DentistService dentistService;

    @MockBean
    private ServicesService servicesService;

    @MockBean
    private AppointmentMapper mapper;

    @MockBean
    private ProxyInterface proxy;

    private final Dentist dentist1 =  new Dentist(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1));
    private final Services service1 = new Services(1L, "Description1", 111.1);
    private final Appointment appointment1 = new Appointment(1L, "Name1", "Surname1", "111", "email1", LocalDateTime.of(2022, 2, 2, 2, 2), dentist1, service1);
    private final Appointment appointment2 = new Appointment(2L, "Name2", "Surname2", "222", "email2", LocalDateTime.of(2022, 2, 2, 2, 2),
                    new Dentist(2L, "DentistName2", "DentistSurname2", LocalDate.of(1998, 2,2)),
                    new Services(2L, "Description2", 222.2));
    private final Appointment appointment3 = new Appointment(3L, "Name3", "Surname3", "333", "email3", LocalDateTime.of(2022, 2, 2, 2, 2),
                    new Dentist(3L, "DentistName3", "DentistSurname3", LocalDate.of(1999, 3,3)),
                    new Services(3L, "Description3", 333.3));

    private final List<Appointment> appointments = List.of(appointment1 ,appointment2, appointment3);

    private final AppointmentDto appointmentDto1 = new AppointmentDto(1L, "Name1", "Surname1", "111", "email1", LocalDateTime.of(2022, 2, 2, 2, 2),
            1L, 1L);
    private final AppointmentDto appointmentDto2 = new AppointmentDto(2L, "Name2", "Surname2", "222", "email2", LocalDateTime.of(2022, 2, 2, 2, 2),
            2L, 2L);
    private final AppointmentDto appointmentDto3 = new AppointmentDto(3L, "Name3", "Surname3", "333", "email3", LocalDateTime.of(2022, 2, 2, 2, 2),
            3L, 3L);

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
        when(appointmentService.getAllAppointments()).thenReturn(appointments);
        when(mapper.mapToAppointmentDtoList(appointments)).thenReturn(appointmentDtos);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/appointment")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Name1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Name2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name", Matchers.is("Name3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname", Matchers.is("Surname1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].surname", Matchers.is("Surname2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].surname", Matchers.is("Surname3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pesel", Matchers.is("111")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].pesel", Matchers.is("222")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].pesel", Matchers.is("333")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("email1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("email2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].email", Matchers.is("email3")))
                /*Dentist*/
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dentistId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dentistId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].dentistId", Matchers.is(3)))
                /*Service*/
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].serviceId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].serviceId", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].serviceId", Matchers.is(3)));
    }

    @Test
    void shouldFetchAppointment() throws Exception {
        // Given
        when(appointmentService.getAppointment(any(long.class))).thenReturn(appointment1);
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("email1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.is("2022-02-02T02:02:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dentistId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.serviceId", Matchers.is(1)));
    }

    @Test
    void shouldDeleteAppointment() throws Exception {
        // Given
        when(appointmentService.getAppointment(any(long.class))).thenReturn(appointment1);
        doNothing().when(appointmentService).deleteAppointment(any(Appointment.class));

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
        when(appointmentService.saveAppointment(any(Appointment.class))).thenReturn(appointment1);
        doNothing().when(proxy).sendAnEmail(any(Appointment.class));



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
        when(appointmentService.getAppointment(any(long.class))).thenReturn(appointment1);
        when(appointmentService.saveAppointment(any(Appointment.class))).thenReturn(appointment1);
        when(dentistService.getDentist(any(long.class))).thenReturn(dentist1);
        when(servicesService.getService(any(long.class))).thenReturn(service1);

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("email1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.is("2022-02-02T02:02:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dentistId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.serviceId", Matchers.is(1)));
    }
}