package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.*;

import static com.tpg.pizzas.domain.Pizza.Type.VEGETARIAN;

final class TheGreek extends Pizza {

    private TheGreek(Builder builder) {

        super(builder, VEGETARIAN, Cheese.ingredient(), BlackOlives.ingredient(), Tomatoes.ingredient(),
                FetaCheese.ingredient(), Pepperoncini.ingredient());
    }

    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        Pizza build() throws InvalidPizzaException {

            validateCrustiness();

            return new TheGreek(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
