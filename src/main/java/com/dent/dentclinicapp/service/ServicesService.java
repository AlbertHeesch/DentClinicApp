package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Services;
import com.dent.dentclinicapp.repository.ServicesDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService {
    @Autowired
    private ServicesDao servicesDao;

    public List<Services> getAllServices()
    {
        return servicesDao.findAll();
    }

    public Services getService(final Long id) throws ElementNotFoundException
    {
        return servicesDao.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public Services saveService(final Services services)
    {
        return servicesDao.save(services);
    }

    public void deleteService(final Services services)
    {
        servicesDao.delete(services);
    }
}
