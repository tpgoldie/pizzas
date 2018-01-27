package com.tpg.pizzas.domain;

public class PizzasAssertions {

    static AmericanHotAssertion assertThat(AmericanHot actual) {

        return new AmericanHotAssertion(actual);
    }

    static TheGreekAssertion assertThat(TheGreek actual) {

        return new TheGreekAssertion(actual);
    }

    static PremiumHawaiianAssertion assertThat(PremiumHawaiian actual) {

        return new PremiumHawaiianAssertion(actual);
    }
}
