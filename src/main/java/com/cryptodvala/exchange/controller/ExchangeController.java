package com.cryptodvala.exchange.controller;

import com.cryptodvala.exchange.dto.ExchangeDto;
import com.cryptodvala.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/exchanges")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    @GetMapping
    public Flux<ExchangeDto> getAllExchanges() {
        return exchangeService.getAllExchanges();
    }

    @GetMapping(params = "sorted")
    public Flux<ExchangeDto> getAllExchangesSortedByMarketPrice() {
        return exchangeService.getAllExchangesSortedByMarketPrice();
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<Mono<ExchangeDto>> getExchangeBySymbol(@PathVariable String symbol) {
        return ResponseEntity.ok(exchangeService.getExchange(symbol));
    }
}