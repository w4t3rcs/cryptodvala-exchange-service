package com.cryptodvala.exchange.dto;

import com.cryptodvala.exchange.entity.Exchange;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeDto implements Serializable {
    private String symbol;
    private float markPrice;

    public static ExchangeDto fromExchange(Exchange exchange) {
        return new ExchangeDto(exchange.getSymbol(), exchange.getMarkPrice());
    }

    public Exchange toExchange() {
        return new Exchange(this.symbol, this.getMarkPrice());
    }
}
