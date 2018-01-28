package com.tpg.pjs.pizzas;

public class PizzasAssertions {

    public static AmericanHotAssertion assertThat(AmericanHot actual) {

        return new AmericanHotAssertion(actual);
    }

    static TheGreekAssertion assertThat(TheGreek actual) {

        return new TheGreekAssertion(actual);
    }

    static PremiumHawaiianAssertion assertThat(PremiumHawaiian actual) {

        return new PremiumHawaiianAssertion(actual);
    }

    static ChickenClubAssertion assertThat(ChickenClub actual) {

        return new ChickenClubAssertion(actual);
    }

    static PapasFavouriteAssertion assertThat(PapasFavourite actual) {

        return new PapasFavouriteAssertion(actual);
    }
}
