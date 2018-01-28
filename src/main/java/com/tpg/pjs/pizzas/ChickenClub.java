package com.tpg.pjs.pizzas;

import com.tpg.pjs.pizzas.ingredients.*;

import static com.tpg.pjs.pizzas.Codes.CHICKEN_CLUB_CODE;
import static com.tpg.pjs.pizzas.Pizza.Type.MEATS;

public final class ChickenClub extends Pizza {

    private ChickenClub(Builder builder) {

        super(builder, MEATS, Cheese.ingredient(), Tomatoes.ingredient(), Onions.ingredient(),
                Chicken.ingredient(), Bacon.ingredient());
    }

    public static class Builder extends Pizza.Builder<Builder> {

        @Override
        public Pizza build() throws InvalidPizzaException {

            name("Chicken Club");

            code(CHICKEN_CLUB_CODE.getValue());

            validateCrustiness();

            return new ChickenClub(this);
        }

        @Override
        protected Builder self() {

            return this;
        }
    }
}
