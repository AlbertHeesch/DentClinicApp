package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.domain.DentistDto;
import com.dent.dentclinicapp.mapper.DentistMapper;
import com.dent.dentclinicapp.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dentist")
@RequiredArgsConstructor
public class DentistController
{
    private final DentistService service;
    private final DentistMapper mapper;

    @GetMapping
    public ResponseEntity<List<DentistDto>> getDentists()
    {
        List<Dentist> dentists = service.getAllDentists();
        return ResponseEntity.ok(mapper.mapToDentistDtoList(dentists));
    }

    @GetMapping(value = "{dentistId}")
    public ResponseEntity<DentistDto> getDentist(@PathVariable Long dentistId) throws ElementNotFoundException {
        return ResponseEntity.ok(mapper.mapToDentistDto(service.getDentist(dentistId)));
    }

    @DeleteMapping(value = "{dentistId}")
    public ResponseEntity<Void> deleteDentist(@PathVariable Long dentistId) throws ElementNotFoundException
    {
        service.deleteDentist(service.getDentist(dentistId));
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDentist(@RequestBody DentistDto dentistDto) throws ElementNotFoundException {
        Dentist dentist = mapper.mapToDentist(dentistDto);
        service.saveDentist(dentist);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<DentistDto> updateDentist(@RequestBody DentistDto dentistDto) throws ElementNotFoundException {
        Dentist dentistToUpdate = service.getDentist(dentistDto.getId());
        dentistToUpdate.setName(dentistDto.getName());
        dentistToUpdate.setSurname(dentistDto.getSurname());
        dentistToUpdate.setExperience(dentistDto.getExperience());
        service.saveDentist(dentistToUpdate);
        return ResponseEntity.ok(dentistDto);
    }
}
