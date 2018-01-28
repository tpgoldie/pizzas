package com.tpg.pjs.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(callSuper = true)
public final class Cost extends Price {

    public Cost(BigDecimal value) {

        super(value);
    }

    public Cost add(Cost cost) {

        return new Cost(this.getPrice().add(cost.getPrice()));
    }
}
