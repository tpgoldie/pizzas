package com.tpg.pjs.persistence.entities;


import org.assertj.core.api.AbstractAssert;

public class OrderEntityAssertion extends AbstractAssert<OrderEntityAssertion, OrderEntity> {

    public OrderEntityAssertion(OrderEntity actual) {

        super(actual, OrderEntityAssertion.class);
    }
}
