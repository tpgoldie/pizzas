package com.tpg.pizzas.domain.ordering;

import com.tpg.pizzas.domain.Pizza;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderedPizzaAssertion extends OrderItemAssertion<OrderedPizza> {

    public OrderedPizzaAssertion(OrderedPizza orderedPizza) {

        super(orderedPizza);
    }

    public OrderedPizzaAssertion hasPizza(Pizza value) {

        assertThat(actual.getPizza()).isEqualTo(value);

        return this;
    }
}
