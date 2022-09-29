package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.repository.DentistDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DentistService {
    private final DentistDao dentistDao;

    public List<Dentist> getAllDentist()
    {
        return dentistDao.findAll();
    }

    public Dentist getDentistById(Long id)
    {
        return new Dentist();
    }
}
