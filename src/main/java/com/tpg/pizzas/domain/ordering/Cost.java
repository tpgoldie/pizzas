package com.tpg.pizzas.domain.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(callSuper = true)
public final class Cost extends Price {

    public Cost(BigDecimal value) {

        super(value);
    }
}
