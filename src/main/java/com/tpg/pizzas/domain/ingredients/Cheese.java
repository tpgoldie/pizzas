package com.tpg.pizzas.domain.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Cheese extends Ingredient {

    private static final Cheese INSTANCE = new Cheese();

    public static Ingredient ingredient() { return INSTANCE; }

    private Cheese() {
        super("cheese");
    }
}
