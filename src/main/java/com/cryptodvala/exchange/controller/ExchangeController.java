package com.cryptodvala.exchange.controller;

import com.cryptodvala.exchange.dto.ExchangeDto;
import com.cryptodvala.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/exchanges")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    @GetMapping
    public List<ExchangeDto> getAllExchanges() {
        return exchangeService.getAllExchanges();
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<ExchangeDto> getExchangeBySymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(exchangeService.getExchange(symbol));
    }
}