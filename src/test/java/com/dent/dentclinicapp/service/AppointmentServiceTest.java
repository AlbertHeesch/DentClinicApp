package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Appointment;
import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.domain.Services;
import com.dent.dentclinicapp.repository.AppointmentDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService service;

    @Mock
    private AppointmentDao repository;

    @Test
    void shouldFetchEmptyList()
    {
        //Given
        List<Appointment> emptyAppointmentList = new ArrayList<>();

        when(repository.findAll()).thenReturn(emptyAppointmentList);

        //When
        List<Appointment> appointmentList = service.getAllAppointments();

        //Then
        assertEquals(0, appointmentList.size());
    }

    @Test
    void getAllAppointments() {
        //Given
        Appointment appointment1 = new Appointment(1L, "Name1", "Surname1", "111", "email1", LocalDateTime.of(2022, 2, 2, 2, 22),
                new Dentist(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1), List.of()),
                new Services(1L, "Description1", 111.1, List.of()));
        Appointment appointment2 =new Appointment(2L, "Name2", "Surname2", "222", "email2", LocalDateTime.of(2022, 2, 2, 2, 23),
                new Dentist(2L, "DentistName2", "DentistSurname2", LocalDate.of(1998, 2,2), List.of()),
                new Services(2L, "Description2", 222.2, List.of()));
        Appointment appointment3 =new Appointment(3L, "Name3", "Surname3", "333", "email3", LocalDateTime.of(2022, 2, 2, 2, 24),
                new Dentist(3L, "DentistName3", "DentistSurname3", LocalDate.of(1999, 3,3), List.of()),
                new Services(3L, "Description3", 333.3, List.of()));

        List<Appointment> appointments = List.of(appointment1 ,appointment2, appointment3);

        when(repository.findAll()).thenReturn(appointments);

        //When
        List<Appointment> appointmentList = service.getAllAppointments();

        //Then
        assertEquals(3, appointmentList.size());
        assertEquals(1L, appointmentList.get(0).getId());
        assertEquals(2L, appointmentList.get(1).getId());
        assertEquals(3L, appointmentList.get(2).getId());
        assertEquals("Name1", appointmentList.get(0).getName());
        assertEquals("Name2", appointmentList.get(1).getName());
        assertEquals("Name3", appointmentList.get(2).getName());
        assertEquals("Surname1", appointmentList.get(0).getSurname());
        assertEquals("Surname2", appointmentList.get(1).getSurname());
        assertEquals("Surname3", appointmentList.get(2).getSurname());
        assertEquals("111", appointmentList.get(0).getPesel());
        assertEquals("222", appointmentList.get(1).getPesel());
        assertEquals("333", appointmentList.get(2).getPesel());
        assertEquals("email1", appointmentList.get(0).getEmail());
        assertEquals("email2", appointmentList.get(1).getEmail());
        assertEquals("email3", appointmentList.get(2).getEmail());
        assertEquals(LocalDateTime.of(2022, 2, 2, 2, 22), appointmentList.get(0).getDate());
        assertEquals(LocalDateTime.of(2022, 2, 2, 2, 23), appointmentList.get(1).getDate());
        assertEquals(LocalDateTime.of(2022, 2, 2, 2, 24), appointmentList.get(2).getDate());
        /*Dentists*/
        assertEquals(1L, appointmentList.get(0).getDentist().getId());
        assertEquals(2L, appointmentList.get(1).getDentist().getId());
        assertEquals(3L, appointmentList.get(2).getDentist().getId());
        assertEquals("DentistName1", appointmentList.get(0).getDentist().getName());
        assertEquals("DentistName2", appointmentList.get(1).getDentist().getName());
        assertEquals("DentistName3", appointmentList.get(2).getDentist().getName());
        assertEquals("DentistSurname1", appointmentList.get(0).getDentist().getSurname());
        assertEquals("DentistSurname2", appointmentList.get(1).getDentist().getSurname());
        assertEquals("DentistSurname3", appointmentList.get(2).getDentist().getSurname());
        assertEquals(LocalDate.of(1997, 1,1), appointmentList.get(0).getDentist().getExperience());
        assertEquals(LocalDate.of(1998, 2,2), appointmentList.get(1).getDentist().getExperience());
        assertEquals(LocalDate.of(1999, 3,3), appointmentList.get(2).getDentist().getExperience());
        assertEquals(0, appointmentList.get(0).getDentist().getAppointmentList().size());
        assertEquals(0, appointmentList.get(1).getDentist().getAppointmentList().size());
        assertEquals(0, appointmentList.get(2).getDentist().getAppointmentList().size());
        /*Services*/
        assertEquals(1L, appointmentList.get(0).getService().getId());
        assertEquals(2L, appointmentList.get(1).getService().getId());
        assertEquals(3L, appointmentList.get(2).getService().getId());
        assertEquals("Description1", appointmentList.get(0).getService().getDescription());
        assertEquals("Description2", appointmentList.get(1).getService().getDescription());
        assertEquals("Description3", appointmentList.get(2).getService().getDescription());
        assertEquals(111.1, appointmentList.get(0).getService().getCost());
        assertEquals(222.2, appointmentList.get(1).getService().getCost());
        assertEquals(333.3, appointmentList.get(2).getService().getCost());
        assertEquals(0, appointmentList.get(0).getService().getAppointmentList().size());
        assertEquals(0, appointmentList.get(1).getService().getAppointmentList().size());
        assertEquals(0, appointmentList.get(2).getService().getAppointmentList().size());
    }

    @Test
    void getAppointment() throws ElementNotFoundException {
        //Given
        Appointment appointment1 = new Appointment(1L, "Name1", "Surname1", "111", "email1", LocalDateTime.of(2022, 2, 2, 2, 22),
                new Dentist(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1), List.of()),
                new Services(1L, "Description1", 111.1, List.of()));

        when(repository.findById(any(long.class))).thenReturn(Optional.of(appointment1));

        //When
        Appointment foundAppointment = service.getAppointment(1L);

        //Then
        assertEquals(1L, foundAppointment.getId());
        assertEquals("Name1", foundAppointment.getName());
        assertEquals("Surname1", foundAppointment.getSurname());
        assertEquals("111", foundAppointment.getPesel());
        assertEquals("email1", foundAppointment.getEmail());
        assertEquals(LocalDateTime.of(2022, 2, 2, 2, 22), foundAppointment.getDate());
        /*Dentist*/
        assertEquals(1L, foundAppointment.getDentist().getId());
        assertEquals("DentistName1", foundAppointment.getDentist().getName());
        assertEquals("DentistSurname1", foundAppointment.getDentist().getSurname());
        assertEquals(LocalDate.of(1997, 1,1), foundAppointment.getDentist().getExperience());
        assertEquals(0, foundAppointment.getDentist().getAppointmentList().size());
        /*Service*/
        assertEquals(1L, foundAppointment.getService().getId());
        assertEquals("Description1", foundAppointment.getService().getDescription());
        assertEquals(111.1, foundAppointment.getService().getCost());
        assertEquals(0, foundAppointment.getService().getAppointmentList().size());
    }

    @Test
    void shouldNotFindAppointment() {
        //Given
        when(repository.findById(any(long.class))).thenReturn(Optional.empty());

        //When&Then
        assertThrows(ElementNotFoundException.class, () -> service.getAppointment(1L));
    }

    @Test
    void saveAppointment() {
        //Given
        Appointment appointment1 = new Appointment(1L, "Name1", "Surname1", "111", "email1", LocalDateTime.of(2022, 2, 2, 2, 22),
                new Dentist(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1), List.of()),
                new Services(1L, "Description1", 111.1, List.of()));

        when(repository.save(any(Appointment.class))).thenReturn(appointment1);

        //When
        Appointment savedAppointment = service.saveAppointment(appointment1);

        //Then
        assertEquals(1L, savedAppointment.getId());
        assertEquals("Name1", savedAppointment.getName());
        assertEquals("Surname1", savedAppointment.getSurname());
        assertEquals("111", savedAppointment.getPesel());
        assertEquals("email1", savedAppointment.getEmail());
        assertEquals(LocalDateTime.of(2022, 2, 2, 2, 22), savedAppointment.getDate());
        /*Dentist*/
        assertEquals(1L, savedAppointment.getDentist().getId());
        assertEquals("DentistName1", savedAppointment.getDentist().getName());
        assertEquals("DentistSurname1", savedAppointment.getDentist().getSurname());
        assertEquals(LocalDate.of(1997, 1,1), savedAppointment.getDentist().getExperience());
        assertEquals(0, savedAppointment.getDentist().getAppointmentList().size());
        /*Service*/
        assertEquals(1L, savedAppointment.getService().getId());
        assertEquals("Description1", savedAppointment.getService().getDescription());
        assertEquals(111.1, savedAppointment.getService().getCost());
        assertEquals(0, savedAppointment.getService().getAppointmentList().size());
    }
}