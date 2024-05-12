package com.cryptodvala.exchange.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Table("exchanges")
public class Exchange implements Persistable<String>, Serializable {
    @Id
    private String symbol;
    private float marketPrice;

    @Override
    public String getId() {
        return this.symbol;
    }

    @Override
    public boolean isNew() {
        return this.symbol == null;
    }
}
