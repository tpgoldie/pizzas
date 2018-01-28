package com.tpg.pizzas.domain.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Onions extends Ingredient {

    private static final Onions INSTANCE = new Onions();

    public static Ingredient ingredient() { return INSTANCE; }

    private Onions() {
        super("onions");
    }
}
