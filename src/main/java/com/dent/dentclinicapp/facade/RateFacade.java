package com.dent.dentclinicapp.facade;

import com.dent.dentclinicapp.controller.ElementNotFoundException;
import com.dent.dentclinicapp.domain.Rate;
import com.dent.dentclinicapp.domain.RateDto;
import com.dent.dentclinicapp.mapper.RateMapper;
import com.dent.dentclinicapp.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RateFacade
{
    private final RateService service;
    private final RateMapper mapper;

    public List<RateDto> fetchRates()
    {
        List<Rate> rates = service.getAllRates();
        return mapper.mapToRateDtoList(rates);
    }

    public RateDto fetchUsdRate() throws ElementNotFoundException {
        return mapper.mapToRateDto(service.getRateByName("USD"));
    }

    public RateDto fetchEurRate() throws ElementNotFoundException {
        return mapper.mapToRateDto(service.getRateByName("EUR"));
    }

    public RateDto fetchTaxRate() throws ElementNotFoundException {
        return mapper.mapToRateDto(service.getRateByName("TAX"));
    }

    public void deleteRate(@PathVariable Long rateId) throws ElementNotFoundException {
        service.deleteRate(service.getRate(rateId));
    }

    public void createRate(@RequestBody RateDto rateDto) {
        Rate rate = mapper.mapToRate(rateDto);
        service.saveRate(rate);
    }

    public RateDto updateRate(@RequestBody RateDto rateDto) {
        Rate rate = mapper.mapToRate(rateDto);
        Rate savedRate = service.saveRate(rate);
        return mapper.mapToRateDto(savedRate);
    }

}
