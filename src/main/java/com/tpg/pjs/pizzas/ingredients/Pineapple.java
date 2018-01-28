package com.tpg.pjs.pizzas.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Pineapple extends Ingredient {

    private static final Pineapple INSTANCE = new Pineapple();

    public static Ingredient ingredient() { return INSTANCE; }

    private Pineapple() {
        super("pineapple");
    }
}
