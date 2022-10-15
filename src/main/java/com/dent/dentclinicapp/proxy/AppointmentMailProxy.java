package com.dent.dentclinicapp.proxy;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.AppointmentDto;
import com.dent.dentclinicapp.service.AppointmentService;
import com.dent.dentclinicapp.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentMailProxy implements ProxyInterface
{
    private final AppointmentService appointmentService;
    private final SimpleEmailService service;
    private ProxyInterface proxyInterface;

    @Override
    public void sendAnEmail(AppointmentDto appointmentDto) throws ElementNotFoundException {
        if(proxyInterface == null)
        { proxyInterface = new EmailSend(service); }
        log.info("Starting appointment creation check in database...");
        if(Optional.ofNullable(appointmentService.getAppointment(appointmentDto.getId())).isPresent()) {
            log.info("Check completed...");
            proxyInterface.sendAnEmail(appointmentDto);
        } else {
            log.info("Ops.. Theres no such appointment. Check database.");
        }
    }
}
