package com.tpg.pizzas.domain;

import com.tpg.pizzas.domain.ingredients.Cheese;
import com.tpg.pizzas.domain.ingredients.JalapenoPeppers;
import com.tpg.pizzas.domain.ingredients.Pepperoni;

import static com.tpg.pizzas.domain.Pizza.Type.MEATS;

final class AmericanHot extends Pizza {

    private AmericanHot(Builder builder) {

        super(builder, MEATS, Cheese.ingredient(), Pepperoni.ingredient(), JalapenoPeppers.ingredient());
    }

    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        Pizza build() throws InvalidPizzaException {

            validateCrustiness();

            return new AmericanHot(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
