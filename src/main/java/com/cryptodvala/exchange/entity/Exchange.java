package com.cryptodvala.exchange.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.Objects;

@ToString
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "exchanges")
public class Exchange implements Persistable<String>, Serializable {
    @Id
    @Column(nullable = false)
    private String symbol;
    @Column(nullable = false)
    private float marketPrice;

    @Override
    public String getId() {
        return this.symbol;
    }

    @Override
    public boolean isNew() {
        return this.symbol == null;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Exchange exchange = (Exchange) o;
        return getSymbol() != null && Objects.equals(getSymbol(), exchange.getSymbol());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
