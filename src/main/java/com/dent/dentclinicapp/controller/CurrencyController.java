package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Currency;
import com.dent.dentclinicapp.domain.CurrencyDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/currency")
public class CurrencyController
{
    @GetMapping
    public List<CurrencyDto> getCurrencies()
    {
        return new ArrayList<>();
    }

    @GetMapping(value = "{currencyId}")
    public CurrencyDto getCurrency(@PathVariable Long currencyId)
    {
        return new CurrencyDto();
    }

    @PutMapping
    public CurrencyDto updateCurrency(@RequestBody CurrencyDto currencyDto)
    {
        return new CurrencyDto();
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
