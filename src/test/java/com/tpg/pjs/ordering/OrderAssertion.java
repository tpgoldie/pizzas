package com.tpg.pjs.ordering;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderAssertion {

    private final Order actual;

    public OrderAssertion(Order actual) {

        this.actual = actual;
    }

    public OrderAssertion hasUserId(String value) {

        assertThat(actual.getUserId()).isEqualTo(value);

        return this;
    }

    public OrderAssertion hasSessionId(String value) {

        assertThat(actual.getSessionId()).isEqualTo(value);

        return this;
    }

    public OrderAssertion hasOrderItems(List<OrderItem> value) {

        assertThat(actual.getOrderedItems().size()).isEqualTo(value.size());

        value.forEach(v -> assertThat(actual.getOrderedItems()).contains(v));

        return this;
    }
}
