package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Currency;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/currency")
public class CurrencyController
{
    @GetMapping
    public List<Currency> getCurrencies()
    {
        return new ArrayList<>();
    }

    @GetMapping(value = "{currencyId}")
    public Currency getCurrency(@PathVariable Long currencyId)
    {
        return new Currency();
    }

    @PutMapping
    public Currency updateCurrency(@RequestBody Currency currency)
    {
        return new Currency();
    }

    @DeleteMapping(value = "{currencyId}")
    public void deleteCurrency(@PathVariable Long currencyId)
    {

    }

    @PostMapping
    public void createCurrency(@PathVariable Long currencyId)
    {

    }
}
