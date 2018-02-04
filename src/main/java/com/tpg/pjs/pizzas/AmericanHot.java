package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.ingredients.Cheese;
import com.tpg.pjs.pizzas.ingredients.JalapenoPeppers;
import com.tpg.pjs.pizzas.ingredients.Pepperoni;

import static com.tpg.pjs.pizzas.PizzaCode.AMERICAN_HOT_CODE;
import static com.tpg.pjs.pizzas.Pizza.Type.MEATS;

public final class AmericanHot extends Pizza {

    private AmericanHot(Builder builder) {

        super(builder, MEATS, Cheese.ingredient(), Pepperoni.ingredient(), JalapenoPeppers.ingredient());
    }

    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        public Pizza build() throws InvalidPizzaException {

            name("American Hot");

            itemCode(AMERICAN_HOT_CODE.getValue());

            validateCrustiness();

            return new AmericanHot(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
