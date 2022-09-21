package com.dent.dentclinicapp.domain.services;

public abstract class AbstractDentServiceDecorator implements DentService
{
    private DentService dentService;

    public AbstractDentServiceDecorator(DentService dentService) {
        this.dentService = dentService;
    }

    @Override
    public double getCost()
    {
        return dentService.getCost();
    }

    @Override
    public String getDescription()
    {
        return dentService.getDescription();
    }
}
