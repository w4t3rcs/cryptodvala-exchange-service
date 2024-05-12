package com.cryptodvala.exchange.repository;

import com.cryptodvala.exchange.entity.Exchange;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ExchangeRepository extends ReactiveCrudRepository<Exchange, String> {
    Flux<Exchange> findAllByOrderByMarketPriceDesc();
}
