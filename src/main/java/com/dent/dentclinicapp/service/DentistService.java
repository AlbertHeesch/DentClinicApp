package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Dentist;
import com.dent.dentclinicapp.repository.DentistDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DentistService {
    @Autowired
    private final DentistDao dentistDao;

    public List<Dentist> getAllDentists()
    {
        return dentistDao.findAll();
    }

    public Dentist getDentist(final Long id) throws ElementNotFoundException
    {
        return dentistDao.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public Dentist saveDentist(final Dentist dentist)
    {
        return dentistDao.save(dentist);
    }

    public void deleteDentist(final Dentist dentist)
    {
        dentistDao.delete(dentist);
    }
}
