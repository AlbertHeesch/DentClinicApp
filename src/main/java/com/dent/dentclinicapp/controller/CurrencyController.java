package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Currency;
import com.dent.dentclinicapp.domain.CurrencyDto;
import com.dent.dentclinicapp.mapper.CurrencyMapper;
import com.dent.dentclinicapp.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/currency")
@RequiredArgsConstructor
public class CurrencyController
{
    private final CurrencyService service;
    private final CurrencyMapper mapper;

    @GetMapping
    public ResponseEntity<List<CurrencyDto>> getCurrencies()
    {
        List<Currency> currencies = service.getAllCurrencies();
        return ResponseEntity.ok(mapper.mapToCurrencyDtoList(currencies));
    }

    @GetMapping(value = "{currencyId}")
    public ResponseEntity<CurrencyDto> getCurrency(@PathVariable Long currencyId) throws ElementNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyDto(service.getCurrency(currencyId)));
    }

    @DeleteMapping(value = "{currencyId}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long currencyId) throws ElementNotFoundException
    {
        service.deleteCurrency(service.getCurrency(currencyId));
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCurrency(@RequestBody CurrencyDto currencyDto)
    {
        Currency currency = mapper.mapToCurrency(currencyDto);
        service.saveCurrency(currency);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CurrencyDto> updateCurrency(@RequestBody CurrencyDto currencyDto)
    {
        Currency currency = mapper.mapToCurrency(currencyDto);
        Currency savedCurrency = service.saveCurrency(currency);
        return ResponseEntity.ok(mapper.mapToCurrencyDto(savedCurrency));
    }
}
