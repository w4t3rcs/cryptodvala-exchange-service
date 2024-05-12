package com.cryptodvala.exchange.service.impl;

import com.cryptodvala.exchange.dto.ExchangeDto;
import com.cryptodvala.exchange.entity.Exchange;
import com.cryptodvala.exchange.exception.ExchangeNotFoundException;
import com.cryptodvala.exchange.repository.ExchangeRepository;
import com.cryptodvala.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeRepository exchangeRepository;

    @Override
    @Transactional
    @Caching(cacheable = @Cacheable(key = "#exchange.symbol", value = "ExchangeServiceImpl::getExchange"))
    public Mono<ExchangeDto> saveExchange(ExchangeDto exchange) {
        return exchangeRepository.save(exchange.toExchange())
                .map(ExchangeDto::fromExchange)
                .cache();
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ExchangeDto> getAllExchanges() {
        return exchangeRepository.findAll()
                .map(ExchangeDto::fromExchange)
                .cache();
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ExchangeDto> getAllExchangesSortedByMarketPrice() {
        return exchangeRepository.findAllByOrderByMarketPriceDesc()
                .map(ExchangeDto::fromExchange)
                .cache();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(key = "#symbol", value = "ExchangeServiceImpl::getExchange")
    public Mono<ExchangeDto> getExchange(String symbol) {
        return exchangeRepository.findById(symbol)
                .switchIfEmpty(Mono.just(new Exchange())
                        .doOnNext(exchange -> {throw new ExchangeNotFoundException();}))
                .map(ExchangeDto::fromExchange)
                .cache();
    }
}