package com.dent.dentclinicapp.domain.services.decorators;

import com.dent.dentclinicapp.domain.services.AbstractDentServiceDecorator;
import com.dent.dentclinicapp.domain.services.DentService;

public class RegularCheckDecorator extends AbstractDentServiceDecorator
{

    public RegularCheckDecorator(DentService dentService) {
        super(dentService);
    }

    @Override
    public double getCost()
    {
        return super.getCost() + 50;
    }

    @Override
    public String getDescription()
    {
        return super.getDescription() + "Regular teeth check";
    }
}
