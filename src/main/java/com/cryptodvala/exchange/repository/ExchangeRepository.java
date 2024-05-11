package com.cryptodvala.exchange.repository;

import com.cryptodvala.exchange.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, String> {
}
