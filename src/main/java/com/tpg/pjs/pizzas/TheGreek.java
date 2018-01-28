package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.ingredients.*;

import static com.tpg.pjs.pizzas.Pizza.Type.VEGETARIAN;

public final class TheGreek extends Pizza {

    private TheGreek(Builder builder) {

        super(builder, VEGETARIAN, Cheese.ingredient(), BlackOlives.ingredient(), Tomatoes.ingredient(),
                FetaCheese.ingredient(), Pepperoncini.ingredient());
    }

    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        public Pizza build() throws InvalidPizzaException {

            validateCrustiness();

            name("The Greek");

            return new TheGreek(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
