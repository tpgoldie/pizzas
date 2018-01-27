package com.tpg.pizzas.domain.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class BlackOlives extends Ingredient {

    private static final BlackOlives INSTANCE = new BlackOlives();

    public static Ingredient ingredient() { return INSTANCE; }

    private BlackOlives() {
        super("black olives");
    }
}
