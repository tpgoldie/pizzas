package com.tpg.pjs.ordering;

import com.tpg.pjs.pizzas.Pizza;

import static org.assertj.core.api.Assertions.assertThat;

final class OrderedPizzaAssertion extends OrderItemAssertion<OrderedPizza> {

    OrderedPizzaAssertion(OrderedPizza orderedPizza) {

        super(orderedPizza);
    }

    OrderedPizzaAssertion hasPizza(Pizza value) {

        assertThat(actual.getPizza()).isEqualTo(value);

        return this;
    }
}
