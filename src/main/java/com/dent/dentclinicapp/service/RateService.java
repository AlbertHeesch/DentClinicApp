package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Rate;
import com.dent.dentclinicapp.repository.RateDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateDao rateDao;

    public List<Rate> getAllRates()
    {
        return rateDao.findAll();
    }

    public Rate getRateByName(final String name) throws ElementNotFoundException {
        return rateDao.findByName(name).orElseThrow(ElementNotFoundException::new);
    }

    public Rate getRate(final Long id) throws ElementNotFoundException {
        return rateDao.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public Rate saveRate(final Rate rate)
    {
        return rateDao.save(rate);
    }

    public void deleteRate(final Rate rate)
    {
        rateDao.delete(rate);
    }
}
