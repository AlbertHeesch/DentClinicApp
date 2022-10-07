package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.domain.Services;
import com.dent.dentclinicapp.repository.ServicesDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicesServiceTest {

    @InjectMocks
    private ServicesService service;

    @Mock
    private ServicesDao servicesDao;

    @Test
    void getAllServices()
    {
        //Given
        Services services1 = new Services(1L, "description1", 1.0, List.of());
        Services services2 = new Services(2L, "description2", 2.0, List.of());
        Services services3 = new Services(3L, "description3", 3.0, List.of());

        List<Services> servicesList = new ArrayList<>();
        servicesList.add(services1);
        servicesList.add(services2);
        servicesList.add(services3);

        when(servicesDao.findAll()).thenReturn(servicesList);

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
    void getService() {
    }

    @Test
    void saveService() {
    }

    @Test
    void deleteService() {
    }
}