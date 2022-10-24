package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Appointment;
import com.dent.dentclinicapp.domain.AppointmentDto;
import com.dent.dentclinicapp.mapper.AppointmentMapper;
import com.dent.dentclinicapp.proxy.ProxyInterface;
import com.dent.dentclinicapp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController
{
    private final AppointmentService service;
    private final AppointmentMapper mapper;
    private final ProxyInterface proxy;

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAppointments()
    {
        List<Appointment> appointments = service.getAllAppointments();
        return ResponseEntity.ok(mapper.mapToAppointmentDtoList(appointments));
    }

    @GetMapping(value = "{appointmentId}")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable Long appointmentId) throws ElementNotFoundException {
        return ResponseEntity.ok(mapper.mapToAppointmentDto(service.getAppointment(appointmentId)));
    }

    @DeleteMapping(value = "{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long appointmentId) throws ElementNotFoundException
    {
        service.deleteAppointment(service.getAppointment(appointmentId));
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAppointment(@RequestBody AppointmentDto appointmentDto) throws ElementNotFoundException {
        Appointment appointment = mapper.mapToAppointment(appointmentDto);
        service.saveAppointment(appointment);
        proxy.sendAnEmail(appointmentDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<AppointmentDto> updateAppointment(@RequestBody AppointmentDto appointmentDto) throws ElementNotFoundException {
        Appointment appointment = mapper.mapToAppointment(appointmentDto);
        Appointment savedAppointment = service.saveAppointment(appointment);
        return ResponseEntity.ok(mapper.mapToAppointmentDto(savedAppointment));
    }
}
