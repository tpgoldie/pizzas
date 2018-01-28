package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.Pizza;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(callSuper = true)
public final class OrderedPizzaWithStuffedCrust extends OrderItem {

    private final OrderedPizza orderedPizza;

    OrderedPizzaWithStuffedCrust(Builder builder) {

        super(builder);

        orderedPizza = new OrderedPizza.Builder()
                .pizza(builder.pizza).quantity(builder.quantity).unitPrice(builder.pizzaUnitPrice).build();
    }

    @Override
    public Cost getCost() {

        return orderedPizza.getCost().add(super.getCost());
    }

    @Override
    public String toString() {

        return String.format("%s with stuffed crust @ %s", orderedPizza, orderedPizza.getUnitPrice(), getUnitPrice());
    }

    public static class Builder extends OrderItem.Builder<OrderedPizzaWithStuffedCrust.Builder> {

        private Pizza pizza;

        private BigDecimal pizzaUnitPrice;

        public OrderedPizzaWithStuffedCrust.Builder pizza(Pizza value) {

            this.pizza = value;

            return self();
        }

        public OrderedPizzaWithStuffedCrust.Builder pizzaUnitPrice(BigDecimal value) {

            this.pizzaUnitPrice = value;

            return self();
        }

        @Override
        public OrderedPizzaWithStuffedCrust build() {

            return new OrderedPizzaWithStuffedCrust(this);
        }

        @Override
        protected OrderedPizzaWithStuffedCrust.Builder self() {

            return this;
        }
    }
}
