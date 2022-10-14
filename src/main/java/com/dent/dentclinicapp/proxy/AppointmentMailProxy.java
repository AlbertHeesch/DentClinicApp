package com.dent.dentclinicapp.proxy;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.AppointmentDto;
import com.dent.dentclinicapp.service.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class AppointmentMailProxy implements ProxyInterface
{
    private AppointmentService service;

    @Override
    public void sendAnEmail(AppointmentDto appointmentDto) throws ElementNotFoundException {
        log.info("Starting appointment creation check in database...");
        if(Optional.ofNullable(service.getAppointment(appointmentDto.getId())).isPresent()){
            log.info("Check completed...");
            ProxyInterface proxyInterface = new EmailSendDelegate();
            proxyInterface.sendAnEmail(appointmentDto);
        } else {
            log.info("Ops.. Theres no such appointment. Check database.");
        }
    }
}
