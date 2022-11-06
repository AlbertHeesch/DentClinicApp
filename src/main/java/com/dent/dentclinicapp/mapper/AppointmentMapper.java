package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.*;
import com.dent.dentclinicapp.service.DentistService;
import com.dent.dentclinicapp.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentMapper
{
    private final DentistService dentistService;
    private final ServicesService servicesService;

    public Appointment mapToAppointment(final AppointmentDto appointmentDto) throws ElementNotFoundException
    {
        Dentist dentist = dentistService.getDentist(appointmentDto.getDentistId());
        Services service = servicesService.getService(appointmentDto.getServiceId());

        return new Appointment(
                appointmentDto.getId(),
                appointmentDto.getName(),
                appointmentDto.getSurname(),
                appointmentDto.getPesel(),
                appointmentDto.getEmail(),
                appointmentDto.getDate(),
                dentist,
                service
        );
    }

    public AppointmentDto mapToAppointmentDto(final Appointment appointment)
    {
        return new AppointmentDto(
                appointment.getId(),
                appointment.getName(),
                appointment.getSurname(),
                appointment.getPesel(),
                appointment.getEmail(),
                appointment.getDate(),
                appointment.getDentist().getId(),
                appointment.getService().getId()
        );
    }

    public List<AppointmentDto> mapToAppointmentDtoList(final List<Appointment> appointmentList)
    {
        return appointmentList.stream()
                .map(this::mapToAppointmentDto)
                .collect(Collectors.toList());
    }
}
