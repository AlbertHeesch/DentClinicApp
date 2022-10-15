package com.dent.dentclinicapp.proxy;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.AppointmentDto;

public interface ProxyInterface
{
    void sendAnEmail(final AppointmentDto appointmentDto) throws ElementNotFoundException;
}
