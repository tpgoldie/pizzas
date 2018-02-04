package com.tpg.pjs.persistence.entities;


import org.assertj.core.api.AbstractAssert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderEntityAssertion extends AbstractAssert<OrderEntityAssertion, OrderEntity> {

    public OrderEntityAssertion(OrderEntity actual) {

        super(actual, OrderEntityAssertion.class);
    }

    public OrderEntityAssertion hasOrderItems(List<OrderItemEntity> values) {

        assertThat(actual.getOrderItems()).isEqualTo(values);

        return this;
    }
}
