package com.tpg.pjs.ordering;

import com.tpg.pjs.persistence.entities.OrderEntity;
import com.tpg.pjs.persistence.entities.OrderEntityAssertion;

public class OrderingAssertions {

    public static OrderedPizzaAssertion assertThat(OrderedPizza actual) {

        return new OrderedPizzaAssertion(actual);
    }

    public static OrderedPizzaWithStuffedCrustAssertion assertThat(OrderedPizzaWithStuffedCrust actual) {

        return new OrderedPizzaWithStuffedCrustAssertion(actual);
    }

    public static OrderDetailsRequestAssertion assertThat(OrderDetailsRequest actual) {

        return new OrderDetailsRequestAssertion(actual);
    }

    public static OrderAssertion assertThat(Order actual) {

        return new OrderAssertion(actual);
    }

    public static OrderEntityAssertion assertThat(OrderEntity actual) {

        return new OrderEntityAssertion(actual);
    }
}
