package com.dent.dentclinicapp.proxy;

import com.dent.dentclinicapp.domain.Appointment;
import com.dent.dentclinicapp.domain.Mail;
import com.dent.dentclinicapp.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailSend implements ProxyInterface
{
    private final SimpleEmailService service;
    private static final String SUBJECT = "Your dentist appointment.";

    @Override
    public void sendAnEmail(Appointment appointment) {
        service.send(new Mail(appointment.getEmail(), SUBJECT), appointment);
    }
}
