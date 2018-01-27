package com.tpg.pizzas.domain.ordering;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
public abstract class OrderItem {

    private UnitPrice unitPrice;

    private Quantity quantity;

    OrderItem(Builder builder) {

        unitPrice = new UnitPrice(builder.unitPrice);

        quantity = new Quantity(builder.quantity);
    }

    public Cost cost() {

        return null;
    }

    @Override
    public String toString() {
        return String.format("%s @ %s", unitPrice, quantity);
    }

    abstract static class Builder<T extends Builder<T>> {

        private BigDecimal unitPrice;

        private int quantity;

        public T unitPrice(BigDecimal value) {

            this.unitPrice = value;

            return self();
        }

        public T quantity(int value) {

            this.quantity = value;

            return self();
        }

        abstract OrderItem build();

        protected abstract T self();

    }
}
