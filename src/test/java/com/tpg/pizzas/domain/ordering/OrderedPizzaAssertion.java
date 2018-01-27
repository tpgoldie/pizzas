package com.tpg.pizzas.domain.ordering;

import com.tpg.pizzas.domain.Pizza;

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
