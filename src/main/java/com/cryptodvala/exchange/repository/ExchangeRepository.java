package com.cryptodvala.exchange.repository;

import com.cryptodvala.exchange.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeRepository extends JpaRepository<Exchange, String> {
    List<Exchange> findAllByOrderByMarketPriceDesc();
}
