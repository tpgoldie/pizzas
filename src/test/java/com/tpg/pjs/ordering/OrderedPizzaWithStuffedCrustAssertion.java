package com.tpg.pjs.ordering;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderedPizzaWithStuffedCrustAssertion extends OrderItemAssertion<OrderedPizzaWithStuffedCrust> {

    public OrderedPizzaWithStuffedCrustAssertion(OrderedPizzaWithStuffedCrust actual) {

        super(actual);
    }

    public OrderedPizzaWithStuffedCrustAssertion hasOrderedPizza(OrderedPizza value) {

        assertThat(actual.getOrderedPizza()).isEqualTo(value);

        return this;
    }

    public OrderedPizzaWithStuffedCrustAssertion hasUnitPrice(UnitPrice value) {

        assertThat(actual.getUnitPrice()).isEqualTo(value);

        return this;
    }

    public OrderedPizzaWithStuffedCrustAssertion hasQuantity(Quantity value) {

        assertThat(actual.getQuantity()).isEqualTo(value);

        return this;
    }
}
