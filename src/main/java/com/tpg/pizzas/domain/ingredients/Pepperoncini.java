package com.tpg.pizzas.domain.ingredients;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class Pepperoncini extends Ingredient {

    private static final Pepperoncini INSTANCE = new Pepperoncini();

    public static Ingredient ingredient() { return INSTANCE; }

    private Pepperoncini() {
        super("pepperoncini");
    }
}
