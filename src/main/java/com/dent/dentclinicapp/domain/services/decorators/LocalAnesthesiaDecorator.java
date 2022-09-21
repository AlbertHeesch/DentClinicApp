package com.dent.dentclinicapp.domain.services.decorators;

import com.dent.dentclinicapp.domain.services.AbstractDentServiceDecorator;
import com.dent.dentclinicapp.domain.services.DentService;

public class LocalAnesthesiaDecorator extends AbstractDentServiceDecorator
{

    public LocalAnesthesiaDecorator(DentService dentService) {
        super(dentService);
    }

    @Override
    public double getCost()
    {
        return super.getCost() + 100;
    }

    @Override
    public String getDescription()
    {
        return super.getDescription() + "Local anesthesia";
    }
}
