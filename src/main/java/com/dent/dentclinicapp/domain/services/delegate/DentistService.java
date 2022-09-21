package com.dent.dentclinicapp.domain.services.delegate;

import com.dent.dentclinicapp.domain.services.DentService;

public class DentistService implements DentService
{

    @Override
    public double getCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "A dentist appointment";
    }
}
