package com.cryptodvala.exchange.async.impl;

import com.cryptodvala.exchange.async.DatabaseLoader;
import com.cryptodvala.exchange.dto.ExchangeDto;
import com.cryptodvala.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class DatabaseLoaderImpl implements DatabaseLoader {
    private final ExchangeService exchangeService;
    private final WebClient webClient;

    @Override
    @Async
    @Scheduled(fixedDelay = 2500)
    public void load() {
        webClient.get()
                .uri("https://fapi.binance.com/fapi/v1/premiumIndex")
                .retrieve()
                .bodyToFlux(ExchangeDto.class)
                .cache()
                .subscribe(exchangeService::saveExchange);
    }
}
