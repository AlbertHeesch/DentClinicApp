package com.dent.dentclinicapp.exchange.client;

import com.dent.dentclinicapp.domain.ExchangeRateDto;
import com.dent.dentclinicapp.exchange.config.ExchangeConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class ExchangeClient
{
    private final RestTemplate restTemplate;

    private final ExchangeConfig exchangeConfig;

    public ExchangeRateDto getPlnToUsdAndEur() {
        URI url = UriComponentsBuilder.fromHttpUrl(exchangeConfig.getExchangeApiEndpoint() + "/latest")
                .queryParam("access_key", exchangeConfig.getApiKey())
                .queryParam("base", "PLN")
                .queryParam("symbols", "USD,EUR")
                .build()
                .encode()
                .toUri();

        return restTemplate.getForObject(url, ExchangeRateDto.class);
    }
}
