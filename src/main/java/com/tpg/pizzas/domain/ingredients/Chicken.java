package com.tpg.pizzas.domain.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Chicken extends Ingredient {

    private static final Chicken INSTANCE = new Chicken();

    public static Ingredient ingredient() { return INSTANCE; }

    private Chicken() {
        super("chicken");
    }
}
