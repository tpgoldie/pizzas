package com.tpg.pizzas.domain.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Pepperoni extends Ingredient {

    private static final Pepperoni INSTANCE = new Pepperoni();

    public static Ingredient ingredient() { return INSTANCE; }

    private Pepperoni() {
        super("pepperoni");
    }
}
