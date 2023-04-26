package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Appointment;
import com.dent.dentclinicapp.domain.AppointmentDto;
import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.domain.Services;
import com.dent.dentclinicapp.mapper.AppointmentMapper;
import com.dent.dentclinicapp.service.AppointmentService;
import com.dent.dentclinicapp.service.DentistService;
import com.dent.dentclinicapp.service.ServicesService;
import com.dent.dentclinicapp.service.SimpleEmailService;
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
    private final AppointmentService appointmentService;
    private final DentistService dentistService;
    private final ServicesService servicesService;
    private final AppointmentMapper mapper;
    private final SimpleEmailService emailService;

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAppointments()
    {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(mapper.mapToAppointmentDtoList(appointments));
    }

    @GetMapping(value = "{appointmentId}")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable Long appointmentId) throws ElementNotFoundException {
        return ResponseEntity.ok(mapper.mapToAppointmentDto(appointmentService.getAppointment(appointmentId)));
    }

    @DeleteMapping(value = "{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long appointmentId) throws ElementNotFoundException
    {
        appointmentService.deleteAppointment(appointmentService.getAppointment(appointmentId));
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAppointment(@RequestBody AppointmentDto appointmentDto) throws ElementNotFoundException {
        Appointment appointment = mapper.mapToAppointment(appointmentDto);
        appointmentService.saveAppointment(appointment);
        emailService.send(appointment);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<AppointmentDto> updateAppointment(@RequestBody AppointmentDto appointmentDto) throws ElementNotFoundException {
        Appointment appointmentToUpdate = appointmentService.getAppointment(appointmentDto.getId());
        Dentist dentist = dentistService.getDentist(appointmentDto.getDentistId());
        Services service = servicesService.getService(appointmentDto.getServiceId());

        appointmentToUpdate.setName(appointmentDto.getName());
        appointmentToUpdate.setSurname(appointmentDto.getSurname());
        appointmentToUpdate.setPesel(appointmentDto.getPesel());
        appointmentToUpdate.setEmail(appointmentDto.getEmail());
        appointmentToUpdate.setDate(appointmentDto.getDate());
        appointmentToUpdate.setDentist(dentist);
        appointmentToUpdate.setService(service);
        appointmentService.saveAppointment(appointmentToUpdate);
        return ResponseEntity.ok(appointmentDto);
    }
}
