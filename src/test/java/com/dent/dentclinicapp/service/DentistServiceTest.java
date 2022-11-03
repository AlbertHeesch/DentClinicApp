package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.repository.DentistDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DentistServiceTest {

    @InjectMocks
    private DentistService service;

    @Mock
    private DentistDao repository;

    @Test
    void shouldFetchEmptyList()
    {
        //Given
        List<Dentist> emptyDentistList = new ArrayList<>();

        when(repository.findAll()).thenReturn(emptyDentistList);

        //When
        List<Dentist> dentistList = service.getAllDentists();

        //Then
        assertEquals(0, dentistList.size());
    }

    @Test
    void getAllDentists() {
        //Given
        Dentist dentist1 = new Dentist(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1));
        Dentist dentist2 = new Dentist(2L, "DentistName2", "DentistSurname2", LocalDate.of(1998, 2,2));
        Dentist dentist3 = new Dentist(3L, "DentistName3", "DentistSurname3", LocalDate.of(1999, 3,3));

        List<Dentist> dentists = List.of(dentist1, dentist2, dentist3);


        when(repository.findAll()).thenReturn(dentists);

        //When
        List<Dentist> dentistList = service.getAllDentists();

        //Then
        assertEquals(3, dentistList.size());
        assertEquals(1L, dentistList.get(0).getId());
        assertEquals(2L, dentistList.get(1).getId());
        assertEquals(3L, dentistList.get(2).getId());
        assertEquals("DentistName1", dentistList.get(0).getName());
        assertEquals("DentistName2", dentistList.get(1).getName());
        assertEquals("DentistName3", dentistList.get(2).getName());
        assertEquals("DentistSurname1", dentistList.get(0).getSurname());
        assertEquals("DentistSurname2", dentistList.get(1).getSurname());
        assertEquals("DentistSurname3", dentistList.get(2).getSurname());
        assertEquals(LocalDate.of(1997, 1,1), dentistList.get(0).getExperience());
        assertEquals(LocalDate.of(1998, 2,2), dentistList.get(1).getExperience());
        assertEquals(LocalDate.of(1999, 3,3), dentistList.get(2).getExperience());
        assertEquals(0, dentistList.get(0).getAppointmentList().size());
        assertEquals(0, dentistList.get(1).getAppointmentList().size());
        assertEquals(0, dentistList.get(2).getAppointmentList().size());
    }

    @Test
    void getDentist() throws ElementNotFoundException {
        //Given
        Dentist dentist1 = new Dentist(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1));

        when(repository.findById(any(long.class))).thenReturn(Optional.of(dentist1));

        //When
        Dentist foundDentist = service.getDentist(1L);

        //Then
        assertEquals(1L, foundDentist.getId());
        assertEquals("DentistName1", foundDentist.getName());
        assertEquals("DentistSurname1", foundDentist.getSurname());
        assertEquals(LocalDate.of(1997, 1,1), foundDentist.getExperience());
        assertEquals(0, foundDentist.getAppointmentList().size());
    }

    @Test
    void shouldNotFindDentist() {
        //Given
        when(repository.findById(any(long.class))).thenReturn(Optional.empty());

        //When&Then
        assertThrows(ElementNotFoundException.class, () -> service.getDentist(1L));
    }

    @Test
    void saveDentist() {
        //Given
        Dentist dentist1 = new Dentist(1L, "DentistName1", "DentistSurname1", LocalDate.of(1997, 1,1));

        when(repository.save(any(Dentist.class))).thenReturn(dentist1);

        //When
        Dentist savedDentist = service.saveDentist(dentist1);

        //Then
        assertEquals(1L, savedDentist.getId());
        assertEquals("DentistName1", savedDentist.getName());
        assertEquals("DentistSurname1", savedDentist.getSurname());
        assertEquals(LocalDate.of(1997, 1,1), savedDentist.getExperience());
        assertEquals(0, savedDentist.getAppointmentList().size());
    }
}