package com.tpg.pjs.pizzas.ordering;

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

    public Cost getCost() {

        return unitPrice.multiplyBy(quantity);
    }

    @Override
    public String toString() {
        return String.format("%s @ %s", quantity, unitPrice);
    }

    abstract static class Builder<T extends Builder<T>> {

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

        public abstract OrderItem build();

        protected abstract T self();

    }
}
