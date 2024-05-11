package com.cryptodvala.exchange.service;

import com.cryptodvala.exchange.dto.ExchangeDto;

import java.util.List;

public interface ExchangeService {
    ExchangeDto saveExchange(ExchangeDto exchange);

    List<ExchangeDto> getAllExchanges();

    List<ExchangeDto> getAllExchangesSortedByMarketPrice();

    ExchangeDto getExchange(String symbol);
}
