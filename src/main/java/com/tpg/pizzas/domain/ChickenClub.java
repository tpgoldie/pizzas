package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.*;

import static com.tpg.pizzas.domain.Pizza.Type.MEATS;

public final class ChickenClub extends Pizza {

    private ChickenClub(Builder builder) {

        super(builder, MEATS, Cheese.ingredient(), Tomatoes.ingredient(), Onions.ingredient(),
                Chicken.ingredient(), Bacon.ingredient());
    }

    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        public Pizza build() throws InvalidPizzaException {

            name("Chicken Club");

            validateCrustiness();

            return new ChickenClub(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
