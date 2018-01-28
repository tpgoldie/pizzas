package com.tpg.pjs.ordering;


import org.assertj.core.api.AbstractAssert;
import org.junit.Assert;

import java.util.List;

public class OrderDetailsRequestAssertion extends AbstractAssert<OrderDetailsRequestAssertion, OrderDetailsRequest> {

    public OrderDetailsRequestAssertion(OrderDetailsRequest actual) {

        super(actual, OrderDetailsRequestAssertion.class);
    }

    public OrderDetailsRequestAssertion hasUserId(String value) {

        Assert.assertEquals("user id does not match", value, actual.getUserId());

        return this;
    }

    public OrderDetailsRequestAssertion hasOrderItems(List<OrderItemDetails> value) {

        Assert.assertEquals("order items do not match", value, actual.getOrderItems());

        return this;
    }
}
