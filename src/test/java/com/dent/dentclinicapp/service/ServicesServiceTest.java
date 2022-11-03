package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Services;
import com.dent.dentclinicapp.repository.ServicesDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicesServiceTest {

    @InjectMocks
    private ServicesService service;

    @Mock
    private ServicesDao repository;

    @Test
    void shouldFetchEmptyList()
    {
        //Given
        List<Services> emptyServiceList = new ArrayList<>();

        when(repository.findAll()).thenReturn(emptyServiceList);

        //When
        List<Services> serviceList = service.getAllServices();

        //Then
        assertEquals(0, serviceList.size());
    }

    @Test
    void getAllServices()
    {
        //Given
        Services services1 = new Services(1L, "description1", 1.0);
        Services services2 = new Services(2L, "description2", 2.0);
        Services services3 = new Services(3L, "description3", 3.0);

        List<Services> servicesList = List.of(services1, services2, services3);

        when(repository.findAll()).thenReturn(servicesList);

        //When
        List<Services> list = service.getAllServices();

        //Then
        assertEquals(3, list.size());
        assertEquals(1L, list.get(0).getId());
        assertEquals(2L, list.get(1).getId());
        assertEquals(3L, list.get(2).getId());
        assertEquals("description1", list.get(0).getDescription());
        assertEquals("description2", list.get(1).getDescription());
        assertEquals("description3", list.get(2).getDescription());
        assertEquals(1.0, list.get(0).getCost());
        assertEquals(2.0, list.get(1).getCost());
        assertEquals(3.0, list.get(2).getCost());
        assertEquals(0, list.get(0).getAppointmentList().size());
        assertEquals(0, list.get(1).getAppointmentList().size());
        assertEquals(0, list.get(2).getAppointmentList().size());
    }

    @Test
    void getService() throws ElementNotFoundException {
        //Given
        Services services1 = new Services(1L, "description1", 1.0);

        when(repository.findById(any(long.class))).thenReturn(Optional.of(services1));

        //When
        Services foundService = service.getService(1L);

        //Then
        assertEquals(1L, foundService.getId());
        assertEquals("description1", foundService.getDescription());
        assertEquals(1.0, foundService.getCost());
        assertEquals(0, foundService.getAppointmentList().size());
    }

    @Test
    void shouldNotFindService() {
        //Given
        when(repository.findById(any(long.class))).thenReturn(Optional.empty());

        //When&Then
        assertThrows(ElementNotFoundException.class, () -> service.getService(1L));
    }

    @Test
    void saveService() {
        //Given
        Services services1 = new Services(1L, "description1", 1.0);

        when(repository.save(any(Services.class))).thenReturn(services1);

        //When
        Services savedService = service.saveService(services1);

        //Then
        assertEquals(1L, savedService.getId());
        assertEquals("description1", savedService.getDescription());
        assertEquals(1.0, savedService.getCost());
        assertEquals(0, savedService.getAppointmentList().size());
    }
}