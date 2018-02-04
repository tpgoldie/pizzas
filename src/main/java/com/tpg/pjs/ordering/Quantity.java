package com.tpg.pjs.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.management.Query;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
public final class Quantity {

    private final BigDecimal value;

    public Quantity(int value) {

        this.value = new BigDecimal(value);
    }

    @Override
    public String toString() {

        return String.format("%d", value.intValue());
    }
}
