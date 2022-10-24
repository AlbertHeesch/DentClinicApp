package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentMapperTest {

    @Autowired
    private AppointmentMapper mapper;

    @Test
    void mapToAppointment() throws ElementNotFoundException {
        //Given
        DentistDto dentistDto1 = new DentistDto(1L, "dentistName", "dentistSurname", LocalDate.of(2022, 1, 12));
        ServicesDto servicesDto1 = new ServicesDto(3L, "description1", 75.0);
        AppointmentDto appointmentDto = new AppointmentDto(
                1L,
                "name1",
                "surname1",
                "pesel1",
                "email1",
                LocalDate.of(2022, 2, 22),
                dentistDto1,
                servicesDto1
                );

        //When
        Appointment appointment = mapper.mapToAppointment(appointmentDto);

        //Then
        assertEquals(1L, appointment.getId());
        assertEquals("name1", appointment.getName());
        assertEquals("surname1", appointment.getSurname());
        assertEquals("pesel1", appointment.getPesel());
        assertEquals("email1", appointment.getEmail());
        assertEquals(LocalDate.of(2022, 2, 22), appointment.getDate());

        assertEquals(1L, appointment.getDentist().getId());
        assertEquals("dentistName", appointment.getDentist().getName());
        assertEquals("dentistSurname", appointment.getDentist().getSurname());
        assertEquals(LocalDate.of(2022, 1, 12), appointment.getDentist().getExperience());

        assertEquals(3L, appointment.getService().getId());
        assertEquals("description1", appointment.getService().getDescription());
        assertEquals(75.0, appointment.getService().getCost());
    }

    @Test
    void mapToAppointmentDto()
    {
        //Given
        Dentist dentist1 = new Dentist(1L, "dentistName", "dentistSurname", LocalDate.of(2022, 1, 12), List.of());
        Services services1 = new Services(3L, "description1", 75.0, List.of());
        Appointment appointment = new Appointment(
                1L,
                "name1",
                "surname1",
                "pesel1",
                "email1",
                LocalDate.of(2022, 2, 22),
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
        assertEquals(LocalDate.of(2022, 2, 22), appointmentDto.getDate());

        assertEquals(1L, appointmentDto.getDentist().getId());
        assertEquals("dentistName", appointmentDto.getDentist().getName());
        assertEquals("dentistSurname", appointmentDto.getDentist().getSurname());
        assertEquals(LocalDate.of(2022, 1, 12), appointmentDto.getDentist().getExperience());

        assertEquals(3L, appointmentDto.getService().getId());
        assertEquals("description1", appointmentDto.getService().getDescription());
        assertEquals(75.0, appointmentDto.getService().getCost());
    }

    @Test
    void mapToAppointmentDtoList()
    {
        //Given
        Dentist dentist1 = new Dentist(1L, "dentistName", "dentistSurname", LocalDate.of(2022, 1, 12), List.of());
        Services services1 = new Services(3L, "description3", 75.0, List.of());
        Appointment appointment1 = new Appointment(
                1L,
                "name1",
                "surname1",
                "pesel1",
                "email1",
                LocalDate.of(2022, 2, 22),
                dentist1,
                services1
        );

        Appointment appointment2 = new Appointment(
                2L,
                "name2",
                "surname2",
                "pesel2",
                "email2",
                LocalDate.of(2023, 2, 22),
                dentist1,
                services1
        );

        Appointment appointment3 = new Appointment(
                3L,
                "name3",
                "surname3",
                "pesel3",
                "email3",
                LocalDate.of(2024, 2, 22),
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
        assertEquals(LocalDate.of(2022, 2, 22), appointmentDtoList.get(0).getDate());
        assertEquals(LocalDate.of(2023, 2, 22), appointmentDtoList.get(1).getDate());
        assertEquals(LocalDate.of(2024, 2, 22), appointmentDtoList.get(2).getDate());

        assertEquals(1L, appointmentDtoList.get(0).getDentist().getId());
        assertEquals("dentistName", appointmentDtoList.get(1).getDentist().getName());
        assertEquals("dentistSurname", appointmentDtoList.get(2).getDentist().getSurname());
        assertEquals(LocalDate.of(2022, 1, 12),appointmentDtoList.get(0).getDentist().getExperience());

        assertEquals(3L, appointmentDtoList.get(0).getService().getId());
        assertEquals("description3", appointmentDtoList.get(1).getService().getDescription());
        assertEquals(75.0, appointmentDtoList.get(2).getService().getCost());
    }
}