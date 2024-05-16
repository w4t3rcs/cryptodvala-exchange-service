package com.cryptodvala.exchange.repository;

import com.cryptodvala.exchange.entity.Exchange;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface ExchangeRepository extends R2dbcRepository<Exchange, String> {
    Flux<Exchange> findAllByOrderByMarketPriceDesc();
}
