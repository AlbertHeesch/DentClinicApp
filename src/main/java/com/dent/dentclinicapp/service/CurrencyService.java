package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Currency;
import com.dent.dentclinicapp.repository.CurrencyDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    @Autowired
    private final CurrencyDao currencyDao;

    public List<Currency> getAllCurrencies()
    {
        return currencyDao.findAll();
    }

    public Currency getCurrency(final Long id) throws ElementNotFoundException
    {
        return currencyDao.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public Currency saveCurrency(final Currency currency)
    {
        return currencyDao.save(currency);
    }

    public void deleteCurrency(final Currency currency)
    {
        currencyDao.delete(currency);
    }
}
