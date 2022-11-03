package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.domain.DentistDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistMapperTest {

    @Autowired
    private DentistMapper mapper;

    @Test
    void mapToDentist() {
        //Given
        DentistDto dentistDto1 = new DentistDto(1L, "dentist1Name", "dentist1Surname", LocalDate.of(2022, 1, 1));

        //When
        Dentist dentist1 = mapper.mapToDentist(dentistDto1);

        //Then
        assertEquals(1L,dentist1.getId());
        assertEquals("dentist1Name", dentist1.getName());
        assertEquals("dentist1Surname", dentist1.getSurname());
        assertEquals(LocalDate.of(2022, 1, 1), dentist1.getExperience());
    }

    @Test
    void mapToDentistDto()
    {
        //Given
        Dentist dentist1 = new Dentist(1L, "dentist1Name", "dentist1Surname", LocalDate.of(2022, 1, 1));
        //When
        DentistDto dentistDto1 = mapper.mapToDentistDto(dentist1);

        //Then
        assertEquals(1L,dentistDto1.getId());
        assertEquals("dentist1Name", dentistDto1.getName());
        assertEquals("dentist1Surname", dentistDto1.getSurname());
        assertEquals(LocalDate.of(2022, 1, 1), dentistDto1.getExperience());
    }

    @Test
    void mapToDentistDtoList()
    {
        //Given
        Dentist dentist1 = new Dentist(1L, "dentist1Name", "dentist1Surname", LocalDate.of(2022, 1, 1));
        Dentist dentist2 = new Dentist(2L, "dentist2Name", "dentist2Surname", LocalDate.of(2023, 1, 1));
        Dentist dentist3 = new Dentist(3L, "dentist3Name", "dentist3Surname", LocalDate.of(2024, 1, 1));

        List<Dentist> dentistList = new ArrayList<>();
        dentistList.add(dentist1);
        dentistList.add(dentist2);
        dentistList.add(dentist3);

        //When
        List<DentistDto> dentistDtoList = mapper.mapToDentistDtoList(dentistList);

        //Then
        assertEquals(1L, dentistDtoList.get(0).getId());
        assertEquals(2L, dentistDtoList.get(1).getId());
        assertEquals(3L, dentistDtoList.get(2).getId());
        assertEquals("dentist1Name", dentistDtoList.get(0).getName());
        assertEquals("dentist2Name", dentistDtoList.get(1).getName());
        assertEquals("dentist3Name", dentistDtoList.get(2).getName());
        assertEquals("dentist1Surname", dentistDtoList.get(0).getSurname());
        assertEquals("dentist2Surname", dentistDtoList.get(1).getSurname());
        assertEquals("dentist3Surname", dentistDtoList.get(2).getSurname());
        assertEquals(LocalDate.of(2022, 1, 1), dentistDtoList.get(0).getExperience());
        assertEquals(LocalDate.of(2023, 1, 1), dentistDtoList.get(1).getExperience());
        assertEquals(LocalDate.of(2024, 1, 1), dentistDtoList.get(2).getExperience());
    }
}