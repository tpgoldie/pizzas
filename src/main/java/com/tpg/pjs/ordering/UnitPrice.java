package com.tpg.pjs.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang.builder.EqualsBuilder;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(callSuper = true)
public class UnitPrice extends Price {

    public UnitPrice(BigDecimal value) {

        super(value);
    }

    public Cost multiplyBy(Quantity quantity) {

        return new Cost(getPrice().multiply(quantity.getValue()));
    }
}
