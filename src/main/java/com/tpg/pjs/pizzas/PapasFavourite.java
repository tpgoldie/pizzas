package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.ingredients.*;

import static com.tpg.pjs.pizzas.Pizza.Type.MEATS;

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
