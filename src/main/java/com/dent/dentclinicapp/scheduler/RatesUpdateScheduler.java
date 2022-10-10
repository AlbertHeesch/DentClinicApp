package com.dent.dentclinicapp.scheduler;

import com.dent.dentclinicapp.domain.ExchangeRateDto;
import com.dent.dentclinicapp.domain.Rate;
import com.dent.dentclinicapp.domain.TaxRateDto;
import com.dent.dentclinicapp.exchange.client.ExchangeClient;
import com.dent.dentclinicapp.service.RateService;
import com.dent.dentclinicapp.tax.client.TaxClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RatesUpdateScheduler
{
    private final ExchangeClient exchangeClient;
    private final TaxClient taxClient;
    private final RateService service;

    @Scheduled(cron = "0 0 1 * * *")
    public void updateUsd()     /*All in one method to save client's request limit*/
    {
        ExchangeRateDto rates = exchangeClient.getRatesToPln();
        Rate eurRate = new Rate(1L, "EUR", rates.getExchangeRates().getEur());
        Rate usdRate = new Rate(2L, "USD", rates.getExchangeRates().getUsd());
        Rate gbpRate = new Rate(3L, "GBP", rates.getExchangeRates().getGbp());

        log.info("Starting currencies rate update preparation...");
        try {
            service.saveRate(eurRate);
            service.saveRate(usdRate);
            service.saveRate(gbpRate);
            log.info("Currencies rates has been updated successfully!");
        } catch (Exception e) {
            log.error("Failed to process currencies rates update: " + e.getMessage(), e);
        }
    }
    @Scheduled(fixedDelay = 100000)
//    @Scheduled(cron = "0 0 0 * * *")
    public void updateTax()
    {
        TaxRateDto rateDto = taxClient.getPolandTaxRate();
        Rate taxRate = new Rate(4L, "TAX", rateDto.getStandardRateDto().getTaxRate());
        log.info("Starting tax rate update preparation...");
        try {
            service.saveRate(taxRate);
            log.info("Tax rate has been updated successfully!");
        } catch (Exception e) {
            log.error("Failed to process tax rate update: " + e.getMessage(), e);
        }
    }
}
