package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.*;

import static com.tpg.pizzas.domain.Pizza.Type.MEATS;

public final class PremiumHawaiian extends Pizza {

    private PremiumHawaiian(Builder builder) {

        super(builder, MEATS, Cheese.ingredient(), PulledHamHock.ingredient(), Pineapple.ingredient());
    }

    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        public Pizza build() throws InvalidPizzaException {

            validateCrustiness();

            name("Premium Hawaiian");

            return new PremiumHawaiian(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
