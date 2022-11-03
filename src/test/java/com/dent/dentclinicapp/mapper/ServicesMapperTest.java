package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.domain.Services;
import com.dent.dentclinicapp.domain.ServicesDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServicesMapperTest {

    @Autowired
    private ServicesMapper mapper;

    @Test
    void mapToServices() {
        //Given
        ServicesDto servicesDto1 = new ServicesDto(1L, "description1", 1.0);

        //When
        Services services1 = mapper.mapToServices(servicesDto1);

        //Then
        assertEquals(1L, services1.getId());
        assertEquals("description1", services1.getDescription());
        assertEquals(1.0, services1.getCost());
    }

    @Test
    void mapToServicesDto()
    {
        //Given
        Services services1 = new Services(1L, "description1", 1.0);

        //When
        ServicesDto servicesDto1 = mapper.mapToServicesDto(services1);

        //Then
        assertEquals(1L, servicesDto1.getId());
        assertEquals("description1", servicesDto1.getDescription());
        assertEquals(1.0, servicesDto1.getCost());
    }

    @Test
    void mapToServicesDtoList()
    {
        //Given
        Services services1 = new Services(1L, "description1", 1.0);
        Services services2 = new Services(2L, "description2", 2.0);
        Services services3 = new Services(3L, "description3", 3.0);

        List<Services> servicesList = new ArrayList<>();
        servicesList.add(services1);
        servicesList.add(services2);
        servicesList.add(services3);

        //When
        List<ServicesDto> servicesDtoList = mapper.mapToServicesDtoList(servicesList);

        //Then
        assertEquals(1L, servicesDtoList.get(0).getId());
        assertEquals(2L, servicesDtoList.get(1).getId());
        assertEquals(3L, servicesDtoList.get(2).getId());
        assertEquals("description1", servicesDtoList.get(0).getDescription());
        assertEquals("description2", servicesDtoList.get(1).getDescription());
        assertEquals("description3", servicesDtoList.get(2).getDescription());
        assertEquals(1.0, servicesDtoList.get(0).getCost());
        assertEquals(2.0, servicesDtoList.get(1).getCost());
        assertEquals(3.0, servicesDtoList.get(2).getCost());

    }
}