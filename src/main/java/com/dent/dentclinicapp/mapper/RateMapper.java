package com.dent.dentclinicapp.mapper;

import com.dent.dentclinicapp.domain.Rate;
import com.dent.dentclinicapp.domain.RateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateMapper {
    public Rate mapToRate(final RateDto rateDto)
    {
        return new Rate(
                rateDto.getId(),
                rateDto.getName(),
                rateDto.getValue()
        );
    }

    public RateDto mapToRateDto(final Rate rate)
    {
        return new RateDto(
                rate.getId(),
                rate.getName(),
                rate.getValue()
        );
    }

    public List<RateDto> mapToRateDtoList(final List<Rate> rateList)
    {
        return rateList.stream()
                .map(this::mapToRateDto)
                .collect(Collectors.toList());
    }
}
