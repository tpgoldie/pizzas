package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.*;

import static com.tpg.pizzas.domain.Pizza.Type.VEGETARIAN;

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
