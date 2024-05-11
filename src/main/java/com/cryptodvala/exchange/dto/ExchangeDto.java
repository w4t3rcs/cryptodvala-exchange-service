package com.cryptodvala.exchange.dto;

import com.cryptodvala.exchange.entity.Exchange;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ExchangeDto implements Serializable {
    private String symbol;
    @JsonProperty("markPrice")
    private float marketPrice;

    public static ExchangeDto fromExchange(Exchange exchange) {
        return new ExchangeDto(exchange.getSymbol(), exchange.getMarketPrice());
    }

    public Exchange toExchange() {
        return new Exchange(this.symbol, this.getMarketPrice());
    }
}
