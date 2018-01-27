package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.*;

import static com.tpg.pizzas.domain.Pizza.Type.MEATS;

final class PremiumHawaiian extends Pizza {

    private PremiumHawaiian(Builder builder) {

        super(builder, MEATS, Cheese.ingredient(), PulledHamHock.ingredient(), Pineapple.ingredient());
    }

    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        Pizza build() throws InvalidPizzaException {

            validateCrustiness();

            return new PremiumHawaiian(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
