package com.dent.dentclinicapp.domain.services.decorators;

import com.dent.dentclinicapp.domain.services.AbstractDentServiceDecorator;
import com.dent.dentclinicapp.domain.services.DentService;

public class TeethTreatmentDecorator extends AbstractDentServiceDecorator
{

    public TeethTreatmentDecorator(DentService dentService) {
        super(dentService);
    }

    @Override
    public double getCost()
    {
        return super.getCost() + 150;
    }

    @Override
    public String getDescription()
    {
        return super.getDescription() + "Tooth treatment per 1";
    }
}
