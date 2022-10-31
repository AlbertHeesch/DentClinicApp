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

    public Appointment mapToAppointment(final AppointmentDto appointmentDto) throws ElementNotFoundException {
        return new Appointment(
                appointmentDto.getId(),
                appointmentDto.getName(),
                appointmentDto.getSurname(),
                appointmentDto.getPesel(),
                appointmentDto.getEmail(),
                appointmentDto.getDate(),
                new Dentist(
                        appointmentDto.getDentist().getId(),
                        appointmentDto.getDentist().getName(),
                        appointmentDto.getDentist().getSurname(),
                        appointmentDto.getDentist().getExperience(),
                        dentistService.getDentist(appointmentDto.getDentist().getId()).getAppointmentList()
                ),
                new Services(
                        appointmentDto.getService().getId(),
                        appointmentDto.getService().getDescription(),
                        appointmentDto.getService().getCost(),
                        servicesService.getService(appointmentDto.getService().getId()).getAppointmentList()
                )
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
                new DentistDto(
                        appointment.getDentist().getId(),
                        appointment.getDentist().getName(),
                        appointment.getDentist().getSurname(),
                        appointment.getDentist().getExperience()
                ),
                new ServicesDto(
                        appointment.getService().getId(),
                        appointment.getService().getDescription(),
                        appointment.getService().getCost()
                )
        );
    }

    public List<AppointmentDto> mapToAppointmentDtoList(final List<Appointment> appointmentList)
    {
        return appointmentList.stream()
                .map(this::mapToAppointmentDto)
                .collect(Collectors.toList());
    }
}
