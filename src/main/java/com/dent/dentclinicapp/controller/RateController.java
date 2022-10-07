package com.dent.dentclinicapp.controller;

import com.dent.dentclinicapp.domain.Rate;
import com.dent.dentclinicapp.domain.RateDto;
import com.dent.dentclinicapp.facade.RateFacade;
import com.dent.dentclinicapp.mapper.RateMapper;
import com.dent.dentclinicapp.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rate")
@RequiredArgsConstructor
public class RateController
{
    private final RateFacade facade;

    @GetMapping
    public ResponseEntity<List<RateDto>> getRates() {
        return ResponseEntity.ok(facade.fetchRates());
    }

    @GetMapping("/USD")
    public ResponseEntity<RateDto> getUsdRate() throws ElementNotFoundException {
        return ResponseEntity.ok(facade.fetchUsdRate());
    }

    @GetMapping("/EUR")
    public ResponseEntity<RateDto> getEurRate() throws ElementNotFoundException {
        return ResponseEntity.ok(facade.fetchEurRate());
    }

    @GetMapping("/TAX")
    public ResponseEntity<RateDto> getTaxRate() throws ElementNotFoundException {
        return ResponseEntity.ok(facade.fetchTaxRate());
    }

    @DeleteMapping(value = "{rateId}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long rateId) throws ElementNotFoundException {
        facade.deleteRate(rateId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRate(@RequestBody RateDto rateDto) {
        facade.createRate(rateDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<RateDto> updateRate(@RequestBody RateDto rateDto) {
        return ResponseEntity.ok(facade.updateRate(rateDto));
    }
}
