package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Dentist;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/dentist")
public class DentistController
{
    @GetMapping
    public List<Dentist> getDentists()
    {
        return new ArrayList<>();
    }

    @GetMapping(value = "{dentistId}")
    public Dentist getDentist(@PathVariable Long dentistId)
    {
        return new Dentist();
    }

    @DeleteMapping(value = "{dentistId}")
    public void deleteDentist(@PathVariable Long dentistId)
    {

    }

    @PostMapping
    public void createDentist(@RequestBody Dentist dentist)
    {

    }

    @PutMapping
    public Dentist updateDentist(@RequestBody Dentist dentist)
    {
        return new Dentist();
    }
}
