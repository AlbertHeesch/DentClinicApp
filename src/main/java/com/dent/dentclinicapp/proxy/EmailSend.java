package com.dent.dentclinicapp.proxy;

import com.dent.dentclinicapp.domain.AppointmentDto;
import com.dent.dentclinicapp.domain.Mail;
import com.dent.dentclinicapp.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailSend implements ProxyInterface
{
    private final SimpleEmailService service;
    private static final String SUBJECT = "Your dentist appointment.";

    @Override
    public void sendAnEmail(AppointmentDto appointmentDto) {
        service.send(new Mail(appointmentDto.getEmail(), SUBJECT), appointmentDto);
    }
}
