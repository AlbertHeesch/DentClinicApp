package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Appointment;
import com.dent.dentclinicapp.domain.AppointmentDto;
import com.dent.dentclinicapp.mapper.AppointmentMapper;
import com.dent.dentclinicapp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController
{
    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    @GetMapping
    public List<AppointmentDto> getAppointments()
    {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return appointmentMapper.mapToAppointmentDtoList(appointments);
    }

    @GetMapping(value = "{appointmentId}")
    public Appointment getAppointment(@PathVariable Long appointmentId)
    {
        return new Appointment();
    }

    @DeleteMapping(value = "{appointmentId}")
    public void deleteAppointment(@PathVariable Long appointmentId)
    {

    }

    @PostMapping
    public void createAppointment(@RequestBody Appointment appointment)
    {

    }

    @PutMapping
    public Appointment updateAppointment(@RequestBody Appointment appointment)
    {
        return new Appointment();
    }
}
