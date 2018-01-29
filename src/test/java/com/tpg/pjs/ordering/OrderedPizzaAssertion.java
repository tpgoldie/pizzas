package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.Pizza;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

final class OrderedPizzaAssertion extends OrderItemAssertion<OrderedPizza> {

    OrderedPizzaAssertion(OrderedPizza orderedPizza) {

        super(orderedPizza);
    }

    OrderedPizzaAssertion hasPizza(Pizza value) {

        assertThat(actual.getPizza()).isEqualTo(value);

        return this;
    }

    OrderedPizzaAssertion hasCost(Cost value) {

        assertThat(actual.getCost()).isEqualTo(value);

        return this;
    }

    OrderedPizzaAssertion hasQuantity(Quantity value) {

        assertThat(actual.getQuantity()).isEqualTo(value);

        return this;
    }

    OrderedPizzaAssertion hasUnitPrice(UnitPrice value) {

        assertThat(actual.getUnitPrice()).isEqualTo(value);

        return this;
    }
}
