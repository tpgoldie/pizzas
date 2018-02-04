package com.tpg.pjs.pizzas;

public class PizzasAssertions {

    public static AmericanHotAssertion assertThat(AmericanHot actual) {

        return new AmericanHotAssertion(actual);
    }

    public static TheGreekAssertion assertThat(TheGreek actual) {

        return new TheGreekAssertion(actual);
    }

    public static PremiumHawaiianAssertion assertThat(PremiumHawaiian actual) {

        return new PremiumHawaiianAssertion(actual);
    }

    public static ChickenClubAssertion assertThat(ChickenClub actual) {

        return new ChickenClubAssertion(actual);
    }

    public static PapasFavouriteAssertion assertThat(PapasFavourite actual) {

        return new PapasFavouriteAssertion(actual);
    }
}
