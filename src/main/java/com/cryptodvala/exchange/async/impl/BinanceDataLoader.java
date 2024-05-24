package com.cryptodvala.exchange.async.impl;

import com.cryptodvala.exchange.async.DataLoader;
import com.cryptodvala.exchange.dto.ExchangeDto;
import com.cryptodvala.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceDataLoader implements DataLoader {
    private static final String BINANCE_API_URL = "https://fapi.binance.com/fapi/v1/premiumIndex";
    private final ExchangeService exchangeService;
    private final WebClient webClient;

    @Override
    @Async
    @Scheduled(fixedDelay = 2500)
    public void load() {
        webClient.get()
                .uri(BINANCE_API_URL)
                .retrieve()
                .bodyToFlux(ExchangeDto.class)
                .onErrorResume(e -> {
                    log.error("Error while loading data from Binance", e);
                    return Flux.empty();
                }).cache()
                .subscribe(exchangeService::saveExchange);
    }
}
