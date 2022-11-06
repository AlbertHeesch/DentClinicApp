package com.dent.dentclinicapp.proxy;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Appointment;

public interface ProxyInterface
{
    void sendAnEmail(final Appointment appointment) throws ElementNotFoundException;
}
