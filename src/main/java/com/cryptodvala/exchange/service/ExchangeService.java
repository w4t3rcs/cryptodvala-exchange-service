package com.cryptodvala.exchange.service;

import com.cryptodvala.exchange.dto.ExchangeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExchangeService {
    Mono<ExchangeDto> saveExchange(ExchangeDto exchange);

    Flux<ExchangeDto> getAllExchanges();

    Flux<ExchangeDto> getAllExchangesSortedByMarketPrice();

    Mono<ExchangeDto> getExchange(String symbol);
}
