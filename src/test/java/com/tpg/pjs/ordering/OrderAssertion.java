package com.tpg.pjs.ordering;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderAssertion {

    private final Order actual;

    public OrderAssertion(Order actual) {

        this.actual = actual;
    }

    public OrderAssertion hasOrderItems(List<OrderItem> value) {

        assertThat(value).isEqualTo(actual.getItems());

        return this;
    }
}
