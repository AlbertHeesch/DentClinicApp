package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Appointment;
import com.dent.dentclinicapp.repository.AppointmentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppointmentService
{
    @Autowired
    private final AppointmentDao appointmentDao;
    private final DentistService dentistService;
    private final ServicesService servicesService;

    public List<Appointment> getAllAppointments()
    {
        return appointmentDao.findAll();
    }

    public Appointment getAppointment(final Long id) throws ElementNotFoundException
    {
        return appointmentDao.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public Appointment saveAppointment(final Appointment appointment)
    {
        return appointmentDao.save(appointment);
    }

    public void deleteAppointment(final Appointment appointment) throws ElementNotFoundException {
        Long dentistId = appointment.getDentist().getId();
        Long serviceId = appointment.getService().getId();
        appointment.getDentist().getAppointmentList().removeIf(id -> Objects.equals(id.getId(), appointment.getId()));
        dentistService.saveDentist(dentistService.getDentist(dentistId));
        appointment.getService().getAppointmentList().removeIf(id -> Objects.equals(id.getId(), appointment.getId()));
        servicesService.saveService(servicesService.getService(serviceId));
        appointmentDao.delete(appointment);
    }
}
