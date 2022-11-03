package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.*;
import com.dent.dentclinicapp.service.DentistService;
import com.dent.dentclinicapp.service.ServicesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentMapperTest {

    @InjectMocks
    private AppointmentMapper mapper;

    @Mock
    private DentistService dentistService;

    @Mock
    private ServicesService servicesService;

    @Test
    void mapToAppointment() throws ElementNotFoundException {
        //Given
        Dentist dentist1 = new Dentist(1L, "dentistName", "dentistSurname", LocalDate.of(2022, 1, 12));
        Services services1 = new Services(3L, "description1", 75.0);

        AppointmentDto appointmentDto = new AppointmentDto(
                1L,
                "name1",
                "surname1",
                "pesel1",
                "email1",
                LocalDateTime.of(2022, 2, 22, 2, 2),
                "dentistName",
                "dentistSurname",
                "description1",
                75.0
                );

        when(dentistService.getDentistByNameAndSurname(any(String.class), any(String.class))).thenReturn(dentist1);
        when(servicesService.getServiceByDescription(any(String.class))).thenReturn(services1);

        //When
        Appointment appointment = mapper.mapToAppointment(appointmentDto);

        //Then
        assertEquals(1L, appointment.getId());
        assertEquals("name1", appointment.getName());
        assertEquals("surname1", appointment.getSurname());
        assertEquals("pesel1", appointment.getPesel());
        assertEquals("email1", appointment.getEmail());
        assertEquals(LocalDateTime.of(2022, 2, 22, 2, 2), appointment.getDate());
        /*Dentist*/
        assertEquals(1L, appointment.getDentist().getId());
        assertEquals("dentistName", appointment.getDentist().getName());
        assertEquals("dentistSurname", appointment.getDentist().getSurname());
        assertEquals(LocalDate.of(2022, 1, 12), appointment.getDentist().getExperience());
        /*Service*/
        assertEquals(3L, appointment.getService().getId());
        assertEquals("description1", appointment.getService().getDescription());
        assertEquals(75.0, appointment.getService().getCost());
    }

    @Test
    void mapToAppointmentDto() {
        //Given
        Dentist dentist1 = new Dentist(1L, "dentistName", "dentistSurname", LocalDate.of(2022, 1, 12));
        Services services1 = new Services(3L, "description1", 75.0);
        Appointment appointment = new Appointment(
                1L,
                "name1",
                "surname1",
                "pesel1",
                "email1",
                LocalDateTime.of(2022, 2, 22, 2, 2),
                dentist1,
                services1
        );

        //When
        AppointmentDto appointmentDto = mapper.mapToAppointmentDto(appointment);

        //Then
        assertEquals(1L, appointmentDto.getId());
        assertEquals("name1", appointmentDto.getName());
        assertEquals("surname1", appointmentDto.getSurname());
        assertEquals("pesel1", appointmentDto.getPesel());
        assertEquals("email1", appointmentDto.getEmail());
        assertEquals(LocalDateTime.of(2022, 2, 22, 2, 2), appointmentDto.getDate());
        /*Dentist*/
        assertEquals("dentistName", appointmentDto.getDentistName());
        assertEquals("dentistSurname", appointmentDto.getDentistSurname());
        /*Service*/
        assertEquals("description1", appointmentDto.getDescription());
    }

    @Test
    void mapToAppointmentDtoList()
    {
        //Given
        Dentist dentist1 = new Dentist(1L, "dentistName", "dentistSurname", LocalDate.of(2022, 1, 12));
        Services services1 = new Services(3L, "description3", 75.0);
        Appointment appointment1 = new Appointment(
                1L,
                "name1",
                "surname1",
                "pesel1",
                "email1",
                LocalDateTime.of(2022, 2, 22, 2, 2),
                dentist1,
                services1
        );

        Appointment appointment2 = new Appointment(
                2L,
                "name2",
                "surname2",
                "pesel2",
                "email2",
                LocalDateTime.of(2023, 2, 22, 2, 2),
                dentist1,
                services1
        );

        Appointment appointment3 = new Appointment(
                3L,
                "name3",
                "surname3",
                "pesel3",
                "email3",
                LocalDateTime.of(2024, 2, 22, 2, 2),
                dentist1,
                services1
        );

        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment1);
        appointmentList.add(appointment2);
        appointmentList.add(appointment3);

        //When
        List<AppointmentDto> appointmentDtoList = mapper.mapToAppointmentDtoList(appointmentList);

        //Then
        assertEquals(3, appointmentDtoList.size());
        assertEquals(1L, appointmentDtoList.get(0).getId());
        assertEquals(2L, appointmentDtoList.get(1).getId());
        assertEquals(3L, appointmentDtoList.get(2).getId());
        assertEquals("name1", appointmentDtoList.get(0).getName());
        assertEquals("name2", appointmentDtoList.get(1).getName());
        assertEquals("name3", appointmentDtoList.get(2).getName());
        assertEquals("surname1", appointmentDtoList.get(0).getSurname());
        assertEquals("surname2", appointmentDtoList.get(1).getSurname());
        assertEquals("surname3", appointmentDtoList.get(2).getSurname());
        assertEquals("pesel1", appointmentDtoList.get(0).getPesel());
        assertEquals("pesel2", appointmentDtoList.get(1).getPesel());
        assertEquals("pesel3", appointmentDtoList.get(2).getPesel());
        assertEquals("email1", appointmentDtoList.get(0).getEmail());
        assertEquals("email2", appointmentDtoList.get(1).getEmail());
        assertEquals("email3", appointmentDtoList.get(2).getEmail());
        assertEquals(LocalDateTime.of(2022, 2, 22, 2, 2), appointmentDtoList.get(0).getDate());
        assertEquals(LocalDateTime.of(2023, 2, 22, 2, 2), appointmentDtoList.get(1).getDate());
        assertEquals(LocalDateTime.of(2024, 2, 22, 2, 2), appointmentDtoList.get(2).getDate());
        /*Dentists*/
        assertEquals("dentistName", appointmentDtoList.get(1).getDentistName());
        assertEquals("dentistSurname", appointmentDtoList.get(2).getDentistSurname());
        /*Services*/
        assertEquals("description3", appointmentDtoList.get(1).getDescription());
    }
}