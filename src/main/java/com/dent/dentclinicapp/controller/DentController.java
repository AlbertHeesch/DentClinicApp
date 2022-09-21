package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.AppointmentDto;
import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.domain.DentistDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/dent")
public class DentController
{
    @GetMapping
    public List<DentistDto> getDentistsForIndicatedDate(LocalDate localDate)
    {
        return new ArrayList<DentistDto>();
    }

    @GetMapping
    public AppointmentDto getAppointmentByIdAndPesel(@PathVariable int id, @PathVariable int pesel)
    {
        return new AppointmentDto();
    }

    @PostMapping
    public void createNewAppointment(AppointmentDto appointmentDto)
    {}

    @PutMapping
    public AppointmentDto updateAnAppointment(AppointmentDto appointmentDto)
    {
        return new AppointmentDto();
    }

    @DeleteMapping
    public void cancelAnAppointment(Long id, int pesel)
    {}
}
