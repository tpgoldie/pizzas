package com.tpg.pizzas.domain.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
public abstract class Price implements Pricing {

    private final BigDecimal price;

    protected Price(BigDecimal price) {

        this.price = price;
    }

    @Override
    public String toString() {

        return String.format("%.2f", price.doubleValue());
    }
}
