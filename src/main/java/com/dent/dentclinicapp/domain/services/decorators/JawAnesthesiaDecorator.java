package com.dent.dentclinicapp.domain.services.decorators;

import com.dent.dentclinicapp.domain.services.AbstractDentServiceDecorator;
import com.dent.dentclinicapp.domain.services.DentService;

public class JawAnesthesiaDecorator extends AbstractDentServiceDecorator
{
    public JawAnesthesiaDecorator(DentService dentService) {
        super(dentService);
    }

    @Override
    public double getCost()
    {
        return super.getCost() + 200;
    }

    @Override
    public String getDescription()
    {
        return super.getDescription() + "Jaw anesthesia";
    }
}
