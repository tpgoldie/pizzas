package com.tpg.pjs.ordering;

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
}
