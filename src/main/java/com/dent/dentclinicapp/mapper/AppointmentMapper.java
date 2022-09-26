package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.domain.Appointment;
import com.dent.dentclinicapp.domain.AppointmentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentMapper
{
    public Appointment mapToAppointment(final AppointmentDto appointmentDto)
    {
        return new Appointment(
                appointmentDto.getId(),
                appointmentDto.getName(),
                appointmentDto.getSurname(),
                appointmentDto.getPesel(),
                appointmentDto.getEmail(),
                appointmentDto.getDate(),
                appointmentDto.getDentist(),
                appointmentDto.getService()
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
                appointment.getDentist(),
                appointment.getService()
        );
    }

    public List<AppointmentDto> mapToAppointmentDtoList(final List<Appointment> appointmentList)
    {
        return appointmentList.stream()
                .map(this::mapToAppointmentDto)
                .collect(Collectors.toList());
    }
}
