package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.domain.Currency;
import com.dent.dentclinicapp.domain.CurrencyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyMapper {
    public Currency mapToCurrency(final CurrencyDto currencyDto)
    {
        return new com.dent.dentclinicapp.domain.Currency(
                currencyDto.getId(),
                currencyDto.getName(),
                currencyDto.getValue()
        );
    }

    public CurrencyDto mapToCurrencyDto(final Currency currency)
    {
        return new CurrencyDto(
                currency.getId(),
                currency.getName(),
                currency.getValue()
        );
    }

    public List<CurrencyDto> mapToCurrencyDtoList(final List<Currency> currencyList)
    {
        return currencyList.stream()
                .map(this::mapToCurrencyDto)
                .collect(Collectors.toList());
    }
}
