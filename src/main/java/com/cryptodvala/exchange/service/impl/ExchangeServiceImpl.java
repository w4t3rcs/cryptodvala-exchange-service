package com.cryptodvala.exchange.service.impl;

import com.cryptodvala.exchange.dto.ExchangeDto;
import com.cryptodvala.exchange.exception.ExchangeNotFoundException;
import com.cryptodvala.exchange.repository.ExchangeRepository;
import com.cryptodvala.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeRepository exchangeRepository;

    @Override
    @Caching(cacheable = @Cacheable(key = "#exchange.symbol", value = "ExchangeServiceImpl::getExchange"))
    public ExchangeDto saveExchange(ExchangeDto exchange) {
        return ExchangeDto.fromExchange(exchangeRepository.save(exchange.toExchange()));
    }

    @Override
    public List<ExchangeDto> getAllExchanges() {
        return exchangeRepository.findAll()
                .stream()
                .map(ExchangeDto::fromExchange)
                .toList();
    }

    @Override
    public List<ExchangeDto> getAllExchangesSortedByMarketPrice() {
        return exchangeRepository.findAllByOrderByMarketPriceDesc()
                .stream()
                .map(ExchangeDto::fromExchange)
                .toList();
    }

    @Override
    @Cacheable(key = "#symbol", value = "ExchangeServiceImpl::getExchange")
    public ExchangeDto getExchange(String symbol) {
        return ExchangeDto.fromExchange(exchangeRepository.findById(symbol)
                .orElseThrow(ExchangeNotFoundException::new));
    }
}