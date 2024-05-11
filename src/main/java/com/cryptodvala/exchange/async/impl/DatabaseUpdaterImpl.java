package com.cryptodvala.exchange.async.impl;

import com.cryptodvala.exchange.async.DatabaseUpdater;
import com.cryptodvala.exchange.dto.ExchangeDto;
import com.cryptodvala.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DatabaseUpdaterImpl implements DatabaseUpdater {
    private final ExchangeService exchangeService;
    private final RestClient restClient;

    @Override
    @Async
    @Scheduled(fixedDelay = 10000)
    public void update() {
        Arrays.stream(Objects.requireNonNull(restClient.get()
                        .uri("https://fapi.binance.com/fapi/v1/premiumIndex")
                        .retrieve()
                        .body(ExchangeDto[].class)))
                .forEach(exchangeService::createExchange);
    }
}
