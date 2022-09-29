package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.domain.Services;
import com.dent.dentclinicapp.repository.ServicesDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService {
    private final ServicesDao servicesDao;

    public List<Services> getAllServices()
    {
        return servicesDao.findAll();
    }

    public Services getServicesById(Long id)
    {
        return new Services();
    }
}
