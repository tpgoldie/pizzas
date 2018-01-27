package com.tpg.pizzas.domain;

public class PizzasAssertions {

    static AmericanHotPizzaAssertion assertThat(AmericanHot actual) {

        return new AmericanHotPizzaAssertion(actual);
    }

    static TheGreekPizzaAssertion assertThat(TheGreek actual) {

        return new TheGreekPizzaAssertion(actual);
    }
}
