package com.dent.dentclinicapp.domain.services.decorators;

import com.dent.dentclinicapp.domain.services.AbstractDentServiceDecorator;
import com.dent.dentclinicapp.domain.services.DentService;

public class SurgeryRemovalDecorator extends AbstractDentServiceDecorator
{

    public SurgeryRemovalDecorator(DentService dentService) {
        super(dentService);
    }

    @Override
    public double getCost()
    {
        return super.getCost() + 400;
    }

    @Override
    public String getDescription()
    {
        return super.getDescription() + "Surgery tooth removal per 1";
    }
}
