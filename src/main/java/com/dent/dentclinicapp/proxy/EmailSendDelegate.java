package com.dent.dentclinicapp.proxy;

import com.dent.dentclinicapp.domain.AppointmentDto;
import com.dent.dentclinicapp.service.SimpleEmailService;


public class EmailSendDelegate implements ProxyInterface
{
    private SimpleEmailService service;

    @Override
    public void sendAnEmail(AppointmentDto appointmentDto) {
        service.sendAppointmentEmail(appointmentDto);
    }
}
