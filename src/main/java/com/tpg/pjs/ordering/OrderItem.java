package com.tpg.pjs.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
public abstract class OrderItem implements Orderable {

    private UnitPrice unitPrice;

    private Quantity quantity;

    OrderItem(Builder builder) {

        unitPrice = new UnitPrice(builder.unitPrice);

        quantity = new Quantity(builder.quantity);
    }

    public Cost getCost() {

        return unitPrice.multiplyBy(quantity);
    }

    @Override
    public String toString() {
        return String.format("%s @ %s", quantity, unitPrice);
    }

    abstract static class Builder<T extends Builder<T>> implements OrderableBuilding {

        private BigDecimal unitPrice;

        protected int quantity;

        public T unitPrice(BigDecimal value) {

            this.unitPrice = value;

            return self();
        }

        public T quantity(int value) {

            this.quantity = value;

            return self();
        }

        protected abstract T self();

    }
}
