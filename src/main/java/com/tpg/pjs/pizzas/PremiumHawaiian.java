package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.ingredients.*;

import static com.tpg.pjs.pizzas.Pizza.Type.MEATS;

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
