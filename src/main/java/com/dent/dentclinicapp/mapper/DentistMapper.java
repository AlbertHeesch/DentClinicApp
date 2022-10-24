package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.domain.DentistDto;
import com.dent.dentclinicapp.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DentistMapper {

    private final DentistService dentistService;

    public Dentist mapToDentist(final DentistDto dentistDto) throws ElementNotFoundException {
        return new Dentist(
                dentistDto.getId(),
                dentistDto.getName(),
                dentistDto.getSurname(),
                dentistDto.getExperience(),
                dentistService.getDentist(dentistDto.getId()).getAppointmentList()
        );
    }

    public DentistDto mapToDentistDto(final Dentist dentist)
    {
        return new DentistDto(
                dentist.getId(),
                dentist.getName(),
                dentist.getSurname(),
                dentist.getExperience()
        );
    }

    public List<DentistDto> mapToDentistDtoList(final List<Dentist> dentistList)
    {
        return dentistList.stream()
                .map(this::mapToDentistDto)
                .collect(Collectors.toList());
    }
}
