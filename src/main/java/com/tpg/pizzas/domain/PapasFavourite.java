package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.*;

import static com.tpg.pizzas.domain.Pizza.Type.MEATS;

public final class PapasFavourite extends Pizza {

    private PapasFavourite(Builder builder) {

        super(builder, MEATS, Cheese.ingredient(), ItalianSeasoning.ingredient(), ItalianSixCheeseBlend.ingredient(),
                PorkSausage.ingredient(), Pepperoni.ingredient());
    }

    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        public Pizza build() throws InvalidPizzaException {

            name("The Papa's Favourite");

            validateCrustiness();

            return new PapasFavourite(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
