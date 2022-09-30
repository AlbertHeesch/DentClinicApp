package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.domain.Currency;
import com.dent.dentclinicapp.domain.CurrencyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyMapperTest {

    @Autowired
    private CurrencyMapper mapper;

    @Test
    void mapToCurrency()
    {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "currency1", 3.33);

        //When
        Currency currency = mapper.mapToCurrency(currencyDto);

        //Then
        assertEquals(1L, currency.getId());
        assertEquals("currency1", currency.getName());
        assertEquals(3.33, currency.getValue());
    }

    @Test
    void mapToCurrencyDto()
    {
        //Given
        Currency currency = new Currency(1L, "currency1", 3.33);

        //When
        CurrencyDto currencyDto = mapper.mapToCurrencyDto(currency);

        //Then
        assertEquals(1L, currencyDto.getId());
        assertEquals("currency1", currencyDto.getName());
        assertEquals(3.33, currencyDto.getValue());
    }

    @Test
    void mapToCurrencyDtoList()
    {
        //Given
        Currency currency1 = new Currency(1L, "currency1", 3.33);
        Currency currency2 = new Currency(2L, "currency2", 4.44);
        Currency currency3 = new Currency(3L, "currency3", 5.55);

        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(currency1);
        currencyList.add(currency2);
        currencyList.add(currency3);

        //When
        List<CurrencyDto> currencyDtoList = mapper.mapToCurrencyDtoList(currencyList);

        //Then
        assertEquals(3, currencyDtoList.size());
        assertEquals(1L, currencyDtoList.get(0).getId());
        assertEquals(2L, currencyDtoList.get(1).getId());
        assertEquals(3L, currencyDtoList.get(2).getId());
        assertEquals("currency1", currencyDtoList.get(0).getName());
        assertEquals("currency2", currencyDtoList.get(1).getName());
        assertEquals("currency3", currencyDtoList.get(2).getName());
        assertEquals(3.33, currencyDtoList.get(0).getValue());
        assertEquals(4.44, currencyDtoList.get(1).getValue());
        assertEquals(5.55, currencyDtoList.get(2).getValue());
    }
}