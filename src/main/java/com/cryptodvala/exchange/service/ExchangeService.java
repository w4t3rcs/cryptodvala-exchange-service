package com.cryptodvala.exchange.service;

import com.cryptodvala.exchange.dto.ExchangeDto;

import java.util.List;

public interface ExchangeService {
    ExchangeDto createExchange(ExchangeDto exchange);

    List<ExchangeDto> getAllExchanges();

    ExchangeDto getExchange(String symbol);

    ExchangeDto updateExchange(String symbol, ExchangeDto exchange);

    String deleteExchange(String symbol);
}
