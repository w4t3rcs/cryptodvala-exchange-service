package com.cryptodvala.exchange.service.impl;

import com.cryptodvala.exchange.dto.ExchangeDto;
import com.cryptodvala.exchange.entity.Exchange;
import com.cryptodvala.exchange.exception.ExchangeNotFoundException;
import com.cryptodvala.exchange.repository.ExchangeRepository;
import com.cryptodvala.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeRepository exchangeRepository;

    @Override
    @Caching(cacheable = @Cacheable(key = "#exchange.symbol", value = "ExchangeService::getExchange"))
    public ExchangeDto createExchange(ExchangeDto exchange) {
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
    @Cacheable(key = "#symbol", value = "ExchangeService::getExchange")
    public ExchangeDto getExchange(String symbol) {
        return ExchangeDto.fromExchange(exchangeRepository.findById(symbol)
                .orElseThrow(ExchangeNotFoundException::new));
    }

    @Override
    @Caching(put = @CachePut(key = "#symbol", value = "ExchangeService::getExchange"))
    public ExchangeDto updateExchange(String symbol, ExchangeDto exchange) {
        Exchange exchangeFromDb = exchangeRepository.findById(symbol)
                .orElseThrow(ExchangeNotFoundException::new);
        if (exchange.getMarketPrice() != 0) exchangeFromDb.setMarketPrice(exchange.getMarketPrice());

        return ExchangeDto.fromExchange(exchangeRepository.save(exchangeFromDb));
    }

    @Override
    @Caching(evict = @CacheEvict(key = "#symbol", value = "ExchangeService::getExchange"))
    public String deleteExchange(String symbol) {
        exchangeRepository.deleteById(symbol);
        return symbol;
    }
}