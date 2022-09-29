package com.dent.dentclinicapp.service;

import com.dent.dentclinicapp.domain.Currency;
import com.dent.dentclinicapp.repository.CurrencyDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyDao currencyDao;

    public List<Currency> getAllCurrency()
    {
        return currencyDao.findAll();
    }

    public Currency getCurrencyById(Long id)
    {
        return new Currency();
    }
}
