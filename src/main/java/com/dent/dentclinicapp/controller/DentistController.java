package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.domain.DentistDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/dentist")
public class DentistController
{
    @GetMapping
    public List<DentistDto> getDentists()
    {
        return new ArrayList<>();
    }

    @GetMapping(value = "{dentistId}")
    public DentistDto getDentist(@PathVariable Long dentistId)
    {
        return new DentistDto();
    }

    @DeleteMapping(value = "{dentistId}")
    public void deleteDentist(@PathVariable Long dentistId)
    {

    }

    @PostMapping
    public void createDentist(@RequestBody DentistDto dentistDto)
    {

    }

    @PutMapping
    public DentistDto updateDentist(@RequestBody DentistDto dentistDto)
    {
        return new DentistDto();
    }
}
