package com.tpg.pjs.ordering;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class OrderItemAssertion<U extends OrderItem> extends AbstractAssert<OrderItemAssertion<U>, U> {

    OrderItemAssertion(OrderItem actual) {

        super((U) actual, OrderItemAssertion.class);
    }

    OrderItemAssertion hasUnitPrice(UnitPrice value) {

        assertThat(actual.getUnitPrice()).isEqualTo(value);

        return this;
    }

    OrderItemAssertion hasQuantity(Quantity value) {

        assertThat(actual.getQuantity()).isEqualTo(value);

        return this;
    }

    OrderItemAssertion hasCost(Cost value) {

        assertThat(actual.getCost()).isEqualTo(value);

        return this;
    }
}
