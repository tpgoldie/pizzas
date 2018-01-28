package com.tpg.pjs.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
public abstract class Price implements Pricing {

    private final BigDecimal price;

    Price(BigDecimal price) {

        this.price = price.setScale(PRICING_SCALE, PRICING_ROUNDING_MODE);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) { return true; }
        if (!(obj instanceof Price)) { return false; }

        Price that = (Price) obj;

        return new EqualsBuilder()
                .append(that.price.equals(this.price), true)
                .isEquals();
    }

    @Override
    public String toString() {

        return String.format("%.2f", price.doubleValue());
    }
}
