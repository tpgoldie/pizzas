package com.tpg.pizzas.domain.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Tomatoes extends Ingredient {

    private static final Tomatoes INSTANCE = new Tomatoes();

    public static Ingredient ingredient() { return INSTANCE; }

    private Tomatoes() {
        super("tomatoes");
    }
}
