package com.tpg.pizzas.domain;

public class PizzasAssertions {

    public static AmericanHotPizzaAssertion assertThat(AmericanHot actual) {
        return new AmericanHotPizzaAssertion(actual);
    }
}
