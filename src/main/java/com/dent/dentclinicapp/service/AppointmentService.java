package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.domain.Appointment;
import com.dent.dentclinicapp.repository.AppointmentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService
{
    private final AppointmentDao appointmentDao;

    public List<Appointment> getAllAppointments()
    {
        return appointmentDao.findAll();
    }

    public Appointment getAppointmentById(Long id)
    {
        return new Appointment();
    }
}
